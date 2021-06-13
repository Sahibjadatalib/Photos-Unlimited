package com.example.wallpaperapp.ui.favourite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wallpaperapp.databinding.FavouritesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    companion object {
        fun newInstance() = FavouritesFragment()
    }

    private lateinit var viewModel: FavouritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FavouritesFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(FavouritesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        return binding.root
    }

}