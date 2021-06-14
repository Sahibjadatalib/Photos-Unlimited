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

    var photos = repository.getPexelPhotos().cachedIn(viewModelScope)

}