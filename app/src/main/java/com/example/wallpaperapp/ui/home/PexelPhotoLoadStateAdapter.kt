package com.example.wallpaperapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.databinding.PexelPhotoLoadStateFooterHeaderBinding

class PexelPhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PexelPhotoLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = PexelPhotoLoadStateFooterHeaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class LoadStateViewHolder(private val binding: PexelPhotoLoadStateFooterHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressbar.isVisible = loadState is LoadState.Loading
                errorTextView.isVisible = loadState !is LoadState.Loading
                retryButton.isVisible = loadState !is LoadState.Loading
            }
        }
    }


}