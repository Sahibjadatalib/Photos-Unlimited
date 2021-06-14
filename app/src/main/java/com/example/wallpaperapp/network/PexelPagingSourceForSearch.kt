package com.example.wallpaperapp.network

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wallpaperapp.models.PexelCurated
import com.example.wallpaperapp.models.Photos
import retrofit2.HttpException
import java.io.IOException

private const val SEARCH_RESULT_STARTING_INDEX = 1

class PexelPagingSourceForSearch(private val pexelApi: PexelApi, private val query: String) :
    PagingSource<Int, Photos>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photos> {
        val position = params.key ?: SEARCH_RESULT_STARTING_INDEX

        return try {
            val response = pexelApi.getSearchedPhotos(query, position, params.loadSize)
            val searchedPhotos = response.photos

            LoadResult.Page(
                data = searchedPhotos,
                prevKey = if (position == SEARCH_RESULT_STARTING_INDEX) null else position - 1,
                nextKey = if (searchedPhotos.isEmpty()) null else position + 1
            )
        }catch (exception:IOException){
            LoadResult.Error(exception)
        }catch (exception:HttpException){
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photos>): Int? {
        TODO("Not yet implemented")
    }
}