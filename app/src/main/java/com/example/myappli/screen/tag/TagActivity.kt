package com.example.myappli.screen.tag

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myappli.R
import com.example.myappli.common.EventObserver
import com.example.myappli.common.startFacebookIntent
import com.example.myappli.common.startInstagramIntent
import com.example.myappli.common.startTwitterIntent
import com.example.myappli.data.db.AppDatabase
import com.example.myappli.model.Category
import com.example.myappli.model.Tag
import com.google.gson.Gson


class TagActivity : AppCompatActivity() {


    private lateinit var factory: TagViewModelFactory
    private lateinit var viewModel: TagViewModel
    private lateinit var appDatabase: AppDatabase

    private lateinit var tagList: TextView
    private lateinit var tagCount: TextView
    private lateinit var copyBtn: ImageView
    private lateinit var instagramBtn: ImageView
    private lateinit var fbBtn: ImageView
    private lateinit var twiterBtn: ImageView

    private var tagText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        appDatabase = AppDatabase.getAppDataBase(this)!!
        factory = TagViewModelFactory(appDatabase)
        viewModel = ViewModelProvider(this, factory).get(TagViewModel::class.java)

        tagList = findViewById(R.id.tagList)
        tagCount = findViewById(R.id.tagCount)
        copyBtn = findViewById(R.id.copyBtn)
        instagramBtn = findViewById(R.id.instagramBtn)
        fbBtn = findViewById(R.id.fbBtn)
        twiterBtn = findViewById(R.id.twiterBtn)

        val cat = intent.getStringExtra("cat")

        val category = Gson().fromJson<Category>(cat, Category::class.java)

        supportActionBar?.title = category.name

        viewModel.tagFetchSuccess.observe(this, EventObserver {
            bindData(it)
        })

        viewModel.getTags(category)


        copyBtn.setOnClickListener {
            val clipboard: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", tagText)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }

        instagramBtn.setOnClickListener { startInstagramIntent(tagText) }
        fbBtn.setOnClickListener { startFacebookIntent(tagText) }
        twiterBtn.setOnClickListener { startTwitterIntent(tagText) }

    }

    private fun bindData(tag: Tag) {
        tagText = tag.tag
        tagList.text = tag.tag
        val count = tag.tag.count { "#".contains(it) }
        tagCount.text = "$count TAGS"
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



}