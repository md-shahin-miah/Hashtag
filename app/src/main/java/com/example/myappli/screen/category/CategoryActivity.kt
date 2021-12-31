package com.example.myappli.screen.category

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myappli.R
import com.example.myappli.common.*
import com.example.myappli.data.db.AppDatabase
import com.example.myappli.model.Category
import com.example.myappli.screen.subCategory.SubCategoryActivity
import com.google.gson.Gson


class CategoryActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {


    companion object {
        private const val TAG = "MainActivity"
    }

    private val FILE_NAME = "example.txt"
    private lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var appDatabase: AppDatabase

    private lateinit var recyclerView: RecyclerView

    private var categoryAdapter: CategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        appDatabase = AppDatabase.getAppDataBase(this)!!
        factory = MainViewModelFactory(appDatabase)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        recyclerView = findViewById(R.id.categoryList)

        viewModel.categoryFetchSuccess.observe(this, EventObserver { categories ->
            categoryAdapter?.setNewList(categories)
        })

        viewModel.getCategories(0)


        categoryAdapter = CategoryAdapter(this)
        categoryAdapter?.setListener(this)
        recyclerView.also {
            it.hasFixedSize()
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = categoryAdapter
        }

       // getCategoryData()
      //  getData()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MenuInflater(this).inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_privacy) {
            openPrivacyActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getCategoryData() {
        val agentsJson = getJsonDataFromAsset(applicationContext, "category.json")
        val categories = getCategoryString(agentsJson).toMutableList()
        viewModel.storeCategories(categories)
    }

    private fun getData() {
        val agentsJsonFileString = getJsonDataFromAsset(applicationContext, "tags.json")
        val list = listFromJsonString(agentsJsonFileString).toMutableList()
        viewModel.storeTags(list)
    }

    override fun onItemClick(category: Category) {
        val intent = Intent(this, SubCategoryActivity::class.java)
        intent.putExtra("cat", Gson().toJson(category))
        startActivity(intent)


    }

}