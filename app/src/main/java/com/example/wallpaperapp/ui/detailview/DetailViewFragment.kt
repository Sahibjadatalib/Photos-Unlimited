package com.example.wallpaperapp.ui.detailview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.wallpaperapp.R
import com.example.wallpaperapp.databinding.DetailViewFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
//class DetailViewFragment : Fragment() {
//
//    companion object {
//        fun newInstance() = DetailViewFragment()
//    }
//
//    private val viewModel by viewModels<DetailViewViewModel>()
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//
//        val binding = DetailViewFragmentBinding.inflate(inflater)
//
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = this
//
//        return binding.root
//    }
//}