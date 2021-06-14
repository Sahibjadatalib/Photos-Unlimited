package com.example.wallpaperapp.ui.search_results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.wallpaperapp.R
import com.example.wallpaperapp.databinding.PhotosListItemBinding
import com.example.wallpaperapp.models.Photos

class PexelSearchPhotoAdapter() :
    PagingDataAdapter<Photos, PexelSearchPhotoAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            PhotosListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentPhoto = getItem(position)
        if (currentPhoto != null) {
            holder.bind(currentPhoto)
        }
    }


    class PhotoViewHolder(private val binding: PhotosListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentPhoto: Photos) {

            binding.apply {
                Glide.with(itemView)
                    .load(currentPhoto.src.large2x)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(
                        RequestOptions().placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_baseline_broken_image_24)
                    )
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(imageView)
            }
        }

    }

    companion object {
        private var PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Photos>() {
            override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean =
                oldItem == newItem

        }
    }


}