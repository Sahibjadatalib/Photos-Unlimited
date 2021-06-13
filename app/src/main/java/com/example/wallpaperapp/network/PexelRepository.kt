package com.example.wallpaperapp.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PexelRepository @Inject constructor(private val pexelApi: PexelApi) {

    fun getPexelPhotos() =
        Pager(config = PagingConfig(pageSize = 80,maxSize = 260,enablePlaceholders = false),
              pagingSourceFactory = {PexelPagingSource(pexelApi)}
             ).liveData
}