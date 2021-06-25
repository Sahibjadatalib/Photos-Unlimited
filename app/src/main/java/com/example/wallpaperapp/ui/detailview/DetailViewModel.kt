package com.example.wallpaperapp.ui.detailview

import android.app.Application
import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.drawToBitmap
import androidx.lifecycle.*
import com.example.wallpaperapp.models.Photos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _selectedPhoto = MutableLiveData<Photos>()
    val selectedPhoto:LiveData<Photos> get() = _selectedPhoto

    private val _setWallpaperEvent = MutableLiveData<Boolean>()
    val setWallpaperEvent:LiveData<Boolean> get() = _setWallpaperEvent

    init {
        _selectedPhoto.value = savedStateHandle["selectedPhoto"]
    }

    fun setWallPaperBtnClicked(){
        _setWallpaperEvent.value = true
    }

    fun setWallPaper(view:View) {
        viewModelScope.launch(Dispatchers.IO) {
            val wallPaperManager:WallpaperManager = WallpaperManager.getInstance(view.context)
            val bitmap: Bitmap = view.drawToBitmap()

            try{
                wallPaperManager.setBitmap(bitmap)

            }catch (e: Exception){
                e.printStackTrace()
            }

        }

    }
}