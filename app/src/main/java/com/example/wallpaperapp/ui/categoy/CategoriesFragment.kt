package com.example.wallpaperapp.ui.categoy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.wallpaperapp.databinding.CategoriesFragmentBinding
import com.example.wallpaperapp.ui.search_results.PexelSearchPhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    companion object {
        fun newInstance() = CategoriesFragment()
    }

    private val viewModel by viewModels<CategoriesViewModel>()
    private lateinit var binding: CategoriesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = CategoriesFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        val adapter = PexelSearchPhotoAdapter()
//
//        binding.apply {
//            recyclerview.adapter = adapter
//        }
//
//        viewModel.searchedPhotos.observe(viewLifecycleOwner){
//            adapter.submitData(viewLifecycleOwner.lifecycle,it)
//        }

        return binding.root
    }

}