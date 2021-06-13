package com.example.wallpaperapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _navigateToDetail = MutableLiveData<Boolean>()
    val navigateToDetail: LiveData<Boolean> get() = _navigateToDetail

    val photos = repository.getPexelPhotos().cachedIn(viewModelScope)

    fun navigateToDetail(){

    }
}