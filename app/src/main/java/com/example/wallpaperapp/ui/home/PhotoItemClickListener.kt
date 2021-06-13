package com.example.wallpaperapp.ui.home

import android.view.View
import com.example.wallpaperapp.models.Photos

interface PhotoItemClickListener {
    fun onClickSlectedItem(view:View,selectedPhoto: Photos)
}