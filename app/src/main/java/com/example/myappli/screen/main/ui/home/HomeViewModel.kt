package com.example.myappli.screen.main.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.example.myappli.common.Event
import com.example.myappli.data.db.AppDatabase
import com.example.myappli.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    private val _categoryFetchSuccess = MutableLiveData<Event<List<Category>>>()
    val categoryFetchSuccess: LiveData<Event<List<Category>>> = _categoryFetchSuccess


 private val _searchCategoryFetchSuccess = MutableLiveData<Event<List<Category>>>()
    val searchCategoryFetchSuccess: LiveData<Event<List<Category>>> = _searchCategoryFetchSuccess

    fun getCategories(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.categoryDAO().getCategories(id).let {
                _categoryFetchSuccess.postValue(Event(it))
            }
        }
    }

    fun getSearchList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.categoryDAO().getAllCategories().let {
                val list : MutableList<Category> = mutableListOf()
                it.forEach {cat ->
                    if (cat.name.startsWith(query,ignoreCase = true)){
                        list.add(cat)
                    }
                }
                _searchCategoryFetchSuccess.postValue(Event(list))
                Log.d("TAG", "getSearchList: $list")

            }
        }
    }




}

class HomeModelFactory(
    private val appDatabase: AppDatabase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(appDatabase) as T
    }
}