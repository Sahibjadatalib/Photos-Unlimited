package com.example.wallpaperapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.wallpaperapp.R
import com.example.wallpaperapp.databinding.FragmentHomeBinding
import com.example.wallpaperapp.databinding.PexelPhotoLoadStateFooterHeaderBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.photos_list_item.view.*

@AndroidEntryPoint
class HomeFragment : Fragment(){
    
    companion object{
        fun newInstance() = HomeFragment()
    }

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = FragmentHomeBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = PexelPhotoAdapter()

        binding.apply {
            recyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PexelPhotoLoadStateAdapter { adapter.retry() },
                footer = PexelPhotoLoadStateAdapter { adapter.retry() }
            )
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }



        return binding.root
    }

//    override fun onClickSlectedItem(view: View, selectedPhoto: Photos) {
//        when(view.id){
//            R.id.image_view -> viewModel.navigateToDetailView(selectedPhoto)
//            else -> Toast.makeText(context,"No view clicked",Toast.LENGTH_SHORT).show()
//        }
//    }
}
