package com.example.wallpaperapp.ui.search_results

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.wallpaperapp.network.PexelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(private val repository:PexelRepository): ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val searchedPhotos = currentQuery.switchMap { queryString ->
        repository.getSearchedPhotos(queryString).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "cats"
    }
}