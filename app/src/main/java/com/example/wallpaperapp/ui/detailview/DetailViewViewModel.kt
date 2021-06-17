package com.example.wallpaperapp.ui.detailview

import android.app.Application
import androidx.lifecycle.*
import com.example.wallpaperapp.models.Photos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {


    private val _selectedPhoto = MutableLiveData<Photos>()
    val selectedPhoto:LiveData<Photos> get() = _selectedPhoto

    private val _selectedPhotoId = MutableLiveData<Long>()
    val selectedPhotoId:LiveData<Long> get() = _selectedPhotoId


    init {
        _selectedPhoto.value = savedStateHandle["selectedPhoto"]
        _selectedPhotoId.value = _selectedPhoto.value?.id

    }

}