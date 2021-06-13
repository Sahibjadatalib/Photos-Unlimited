package com.example.wallpaperapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PexelCurated(
        val page:Int,
        val per_page:Int,
        val photos:List<Photos>,
        val next_page:String
):Parcelable

@Parcelize
data class Photos(
        val id: Long,
        val width:Long,
        val height:Long,
        val url:String,
        val photographer:String,
        val photographer_url:String,
        val photographer_id:Long,
        val avg_color:String,
        val src:Source,
        val liked:Boolean
) : Parcelable{}

@Parcelize
data class Source(
        val original:String,
        val large2x:String,
        val medium:String,
        val large:String,
        val small:String,
        val portrait:String,
        val landscape:String,
        val tiny:String
) : Parcelable