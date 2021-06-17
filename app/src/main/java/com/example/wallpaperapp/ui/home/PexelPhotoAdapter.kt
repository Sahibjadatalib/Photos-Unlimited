package com.example.wallpaperapp.ui.home

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

class PexelPhotoAdapter(private val listener: OnClickListener) :
    PagingDataAdapter<Photos, PexelPhotoAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

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


    inner class PhotoViewHolder(private val binding: PhotosListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener{
                val position = bindingAdapterPosition
                if(position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if(item!=null){
                        listener.onPhotoClick(item)
                    }
                }
            }
        }

        fun bind(currentPhoto: Photos) {
            binding.photo = currentPhoto
            binding.executePendingBindings()
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

    interface OnClickListener{
        fun onPhotoClick(photo:Photos)
    }


}