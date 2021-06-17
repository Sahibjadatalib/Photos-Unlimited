package com.example.wallpaperapp.di

import android.os.Build
import androidx.core.os.bundleOf
import androidx.navigation.NavArgs
import com.example.wallpaperapp.models.Photos
import com.example.wallpaperapp.network.PexelApi
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PexelApiModule {


    @Provides
    fun provideMoshi() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    fun provideLogger() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides()
    fun provideHeaderInterceptor() = Interceptor{chain ->
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("x-device-type", Build.DEVICE)
            .addHeader("Authorization",PexelApi.ACCESS_KEY)
            .addHeader("Accept-Langauge", Locale.getDefault().language)
            .build()

        val response = chain.proceed(request)
        response
    }

    @Provides
    fun provideClient(logger:HttpLoggingInterceptor,headerInterceptor: Interceptor) = OkHttpClient.Builder()
        .addInterceptor(logger)
        .addInterceptor(headerInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi,client: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(PexelApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

    @Provides
    @Singleton
    fun providePexelApi(retrofit: Retrofit):PexelApi =
        retrofit.create(PexelApi::class.java)



}