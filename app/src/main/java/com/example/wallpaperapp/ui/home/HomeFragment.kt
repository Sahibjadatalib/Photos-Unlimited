package com.example.wallpaperapp.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.wallpaperapp.R
import com.example.wallpaperapp.databinding.FragmentHomeBinding
import com.example.wallpaperapp.models.Photos
import com.example.wallpaperapp.ui.search_results.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), PexelPhotoAdapter.OnClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel by viewModels<HomeViewModel>()
    private val searchViewModel by viewModels<SearchViewModel>()
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = PexelPhotoAdapter(this)

        binding.apply {
            recyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PexelPhotoLoadStateAdapter { adapter.retry() },
                footer = PexelPhotoLoadStateAdapter { adapter.retry() }
            )
            retryButton.setOnClickListener{
                adapter.retry()
            }
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressbar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerview.isVisible = loadState.source.refresh is LoadState.NotLoading
                textViewError.isVisible = loadState.source.refresh is LoadState.Error
                retryButton.isVisible = loadState.source.refresh is LoadState.Error

                recyclerview.isVisible = !(loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        adapter.itemCount < 1)

            }


        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchResultFragment(query))
                    Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }


    override fun onPhotoClick(photo: Photos) {
        this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailViewFragment(photo))
    }

}
