package com.example.myappli.screen.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import com.example.myappli.R
import com.example.myappli.common.getCategoryString
import com.example.myappli.common.getJsonDataFromAsset
import com.example.myappli.common.listFromJsonString
import com.example.myappli.data.db.AppDatabase
import com.example.myappli.screen.category.MainViewModel
import com.example.myappli.screen.category.MainViewModelFactory
import com.example.myappli.screen.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var factory: SplashViewModelFactory
    private lateinit var viewModel: SplashViewModel
    private lateinit var appDatabase: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        appDatabase = AppDatabase.getAppDataBase(this)!!
        factory = SplashViewModelFactory(appDatabase)
        viewModel = ViewModelProvider(this, factory).get(SplashViewModel::class.java)

        getCategoryData()
        getData()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)

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

}