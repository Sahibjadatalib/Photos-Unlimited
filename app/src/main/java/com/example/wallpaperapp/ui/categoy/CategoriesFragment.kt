package com.example.wallpaperapp.ui.categoy

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wallpaperapp.databinding.CategoriesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    companion object {
        fun newInstance() = CategoriesFragment()
    }

    private lateinit var viewModel: CategoriesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = CategoriesFragmentBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        return binding.root
    }

}