package com.example.wallpaperapp.ui.home

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.wallpaperapp.models.PexelCurated
import com.example.wallpaperapp.models.Photos
import com.example.wallpaperapp.network.PexelApi
import com.example.wallpaperapp.network.PexelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository:PexelRepository) : ViewModel(){

//    private val _navigateToDetail = MutableLiveData<Boolean>()
//    val navigateToDetail: LiveData<Boolean> get() = _navigateToDetail
//
//    private val currentQuery = MutableLiveData<String>()
//
//
//
//

    var photos = repository.getPexelPhotos().cachedIn(viewModelScope)


//    fun searchPhotos(query:String){
//
//        currentQuery.value = query
//        photos = currentQuery.switchMap {queryString->
//            repository.getSearchedPhotos("Ocean").cachedIn(viewModelScope)
//        }
//    }

//    fun navigateToDetail(){
//
//    }
}