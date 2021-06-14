package com.example.wallpaperapp.ui.search_results

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.wallpaperapp.R
import com.example.wallpaperapp.databinding.SearchResultFragmentBinding
import com.example.wallpaperapp.ui.home.PexelPhotoLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SearchResultFragment : Fragment() {

    companion object {
        fun newInstance() = SearchResultFragment()
    }

    private val viewModel by viewModels<SearchResultViewModel>()
    private lateinit var binding: SearchResultFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchResultFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = PexelSearchPhotoAdapter()
        binding.apply {
            recyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
                footer = PexelPhotoLoadStateAdapter {adapter.retry()},
                header = PexelPhotoLoadStateAdapter {adapter.retry()}
            )
        }

        viewModel.searchedPhotos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_search,menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query!=null){
                    binding.recyclerview.scrollToPosition(0)
                    viewModel.searchPhotos(query.lowercase(Locale.getDefault()))
                    Toast.makeText(context,query,Toast.LENGTH_SHORT).show()
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

}