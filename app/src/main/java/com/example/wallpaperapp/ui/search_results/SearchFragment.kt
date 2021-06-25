package com.example.wallpaperapp.ui.search_results

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.wallpaperapp.databinding.SearchResultFragmentBinding
import com.example.wallpaperapp.models.Photos
import com.example.wallpaperapp.ui.home.PexelPhotoLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.search_result_fragment.*
import java.util.*

@AndroidEntryPoint
class SearchFragment : Fragment(), PexelSearchPhotoAdapter.OnClickListener{

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var binding: SearchResultFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchResultFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = PexelSearchPhotoAdapter(this)
        binding.apply {
            recyclerview.itemAnimator = null
            recyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
                footer = PexelPhotoLoadStateAdapter { adapter.retry() },
                header = PexelPhotoLoadStateAdapter { adapter.retry() }
            )
            retryButton.setOnClickListener{
                adapter.retry()
            }
        }

        viewModel.searchedPhotos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressbar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerview.isVisible = loadState.source.refresh is LoadState.NotLoading
                textViewError.isVisible = loadState.source.refresh is LoadState.Error
                retryButton.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1){

                    recyclerview.isVisible = false
                    textViewEmpty.isVisible = true


                }else{
                    recyclerview.isVisible = true
                }

            }


        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onPhotoClick(photo: Photos) {
        this.findNavController().navigate(SearchFragmentDirections.actionSearchResultFragmentToDetailViewFragment(photo))
    }

}