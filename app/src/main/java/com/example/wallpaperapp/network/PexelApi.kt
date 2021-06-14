package com.example.wallpaperapp.network

import android.os.Build
import com.example.wallpaperapp.models.PexelCurated
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.*

interface PexelApi{

    companion object{
        const val BASE_URL = "https://api.pexels.com/v1/"
        const val ACCESS_KEY = "563492ad6f91700001000001efc7527c7c234d13aa5a0656231e042b"

    }

    @GET("curated")
    suspend fun getPexelCurated(
        @Query("page") page:Int,
        @Query("per_page") per_page:Int
    ) : PexelCurated

    @GET("search")
    suspend fun getSearchedPhotos(
        @Query("query") query:String,
        @Query("page") page:Int,
        @Query("per_page") per_page:Int,
    ) : PexelCurated

}