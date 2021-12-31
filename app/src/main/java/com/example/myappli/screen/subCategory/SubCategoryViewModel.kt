package com.example.myappli.screen.subCategory

import androidx.lifecycle.*
import com.example.myappli.common.Event
import com.example.myappli.data.db.AppDatabase
import com.example.myappli.model.Category
import com.example.myappli.model.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubCategoryViewModel(private val appDatabase: AppDatabase) : ViewModel() {



    fun storeCategories(categories : List<Category>){
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.categoryDAO().inserts(categories)
        }
    }

    fun storeTags(tags : List<Tag>){
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.tagDAO().inserts(tags)
        }
    }


    private val _categoryFetchSuccess = MutableLiveData<Event<List<Category>>>()
    val categoryFetchSuccess: LiveData<Event<List<Category>>> = _categoryFetchSuccess


    fun getCategories(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.categoryDAO().getCategories(id).let {
                _categoryFetchSuccess.postValue(Event(it))
            }
        }
    }

    fun getAllCategories(){
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.categoryDAO().getAllCategories().let {
                _categoryFetchSuccess.postValue(Event(it))
            }
        }
    }



}

class SubCategoryViewModelFactory(
    private val appDatabase: AppDatabase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SubCategoryViewModel(appDatabase) as T
    }
}