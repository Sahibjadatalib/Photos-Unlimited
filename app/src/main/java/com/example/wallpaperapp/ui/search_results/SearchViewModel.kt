package com.example.wallpaperapp.ui.search_results

import android.widget.Toast
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.wallpaperapp.network.PexelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository:PexelRepository, private val savedStateHandle: SavedStateHandle): ViewModel() {

    val query:String? = savedStateHandle["query"]

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)


    val searchedPhotos = currentQuery.switchMap { queryString ->
        repository.getSearchedPhotos(queryString).cachedIn(viewModelScope)
    }



    init {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "ocean"
    }
}