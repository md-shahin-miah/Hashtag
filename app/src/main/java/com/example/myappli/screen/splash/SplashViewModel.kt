package com.example.myappli.screen.splash

import androidx.lifecycle.*
import com.example.myappli.common.Event
import com.example.myappli.data.db.AppDatabase
import com.example.myappli.model.Category
import com.example.myappli.model.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SplashViewModel(private val appDatabase: AppDatabase) : ViewModel() {


    fun storeCategories(categories: List<Category>) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.categoryDAO().inserts(categories)
        }
    }

    fun storeTags(tags: List<Tag>) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.tagDAO().inserts(tags)
        }
    }


}

class SplashViewModelFactory(
    private val appDatabase: AppDatabase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel(appDatabase) as T
    }
}