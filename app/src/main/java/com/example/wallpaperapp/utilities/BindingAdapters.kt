package com.example.wallpaperapp.utilities

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.wallpaperapp.R
import com.example.wallpaperapp.models.Photos
import com.example.wallpaperapp.ui.home.PexelPhotoAdapter


@BindingAdapter("imageUrl")
fun bindPhoto(imageView: ImageView,imgUrl:String?){
    imgUrl?.let {
        val uri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imageView.context)
            .load(uri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_baseline_broken_image_24))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}