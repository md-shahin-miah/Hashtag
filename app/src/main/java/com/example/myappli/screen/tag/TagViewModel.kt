package com.example.myappli.screen.tag

import android.util.Log
import androidx.lifecycle.*
import com.example.myappli.common.Event
import com.example.myappli.data.db.AppDatabase
import com.example.myappli.model.Category
import com.example.myappli.model.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TagViewModel(private val appDatabase: AppDatabase) : ViewModel() {


    private val _tagFetchSuccess = MutableLiveData<Event<Tag>>()
    val tagFetchSuccess: LiveData<Event<Tag>> = _tagFetchSuccess


    fun getTags(cat: Category){
        viewModelScope.launch(Dispatchers.IO) {

            Log.d("TAG", "getTags: $cat")

            appDatabase.tagDAO().getTag(cat.name)?.let {

                _tagFetchSuccess.postValue(Event(it))
            }
        }
    }

}

class TagViewModelFactory(
    private val appDatabase: AppDatabase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TagViewModel(appDatabase) as T
    }
}