package com.example.wallpaperapp.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wallpaperapp.models.Photos
import retrofit2.HttpException
import java.io.IOException

private const val PEXEL_STARTING_PAGE_INDEX = 1

class PexelPagingSource(private val pexelApi: PexelApi) : PagingSource<Int,Photos>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photos> {
        val position = params.key ?: PEXEL_STARTING_PAGE_INDEX

        return try{
            val response = pexelApi.getPexelCurated(position,params.loadSize)
            val photos = response.photos

            LoadResult.Page(data = photos,
                            prevKey = if(position == PEXEL_STARTING_PAGE_INDEX) null else position-1,
                            nextKey = if(photos.isEmpty()) null else position+1)


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