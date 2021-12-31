package com.example.myappli.screen.subCategory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myappli.R
import com.example.myappli.common.EventObserver
import com.example.myappli.data.db.AppDatabase
import com.example.myappli.model.Category
import com.example.myappli.screen.category.CategoryAdapter
import com.example.myappli.screen.category.MainViewModel
import com.example.myappli.screen.category.MainViewModelFactory
import com.example.myappli.screen.tag.TagActivity
import com.google.gson.Gson

class SubCategoryActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {

    private lateinit var factory: SubCategoryViewModelFactory
    private lateinit var viewModel: SubCategoryViewModel
    private lateinit var appDatabase: AppDatabase


    private lateinit var recyclerView: RecyclerView

    private var categoryAdapter: CategoryAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        appDatabase = AppDatabase.getAppDataBase(this)!!
        factory = SubCategoryViewModelFactory(appDatabase)
        viewModel = ViewModelProvider(this, factory).get(SubCategoryViewModel::class.java)

        recyclerView = findViewById(R.id.categoryList)

        viewModel.categoryFetchSuccess.observe(this, EventObserver { categories ->
            categoryAdapter?.setNewList(categories)
        })

        val cat = intent.getStringExtra("cat")

        val category = Gson().fromJson<Category>(cat, Category::class.java)

        supportActionBar?.title = category.name


        if (category.id == 1) viewModel.getAllCategories()
        else viewModel.getCategories(category.id)

        categoryAdapter = CategoryAdapter(this)
        categoryAdapter?.setListener(this)
        recyclerView.also {
            it.hasFixedSize()
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = categoryAdapter
        }

    }

    override fun onItemClick(category: Category) {
        val intent = Intent(this, TagActivity::class.java)
        intent.putExtra("cat",Gson().toJson(category))
        startActivity(intent)

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