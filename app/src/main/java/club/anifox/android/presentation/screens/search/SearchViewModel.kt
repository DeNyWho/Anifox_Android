package club.anifox.android.presentation.screens.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import club.anifox.android.data.local.mappers.toAnimeLight
import club.anifox.android.domain.model.anime.light.AnimeLight
import club.anifox.android.domain.usecase.anime.GetAnimePagingUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchViewModel(
    private val animePagingUseCase: GetAnimePagingUseCase,
) : ViewModel() {
    private val _anime: MutableStateFlow<PagingData<AnimeLight>> =
        MutableStateFlow(PagingData.empty())
    val anime: MutableStateFlow<PagingData<AnimeLight>> = _anime

    private var searchJob: Job? = null

    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery: MutableState<String> = _searchQuery

    fun onSearchEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchQueryChange -> {
                if (searchQuery.value != event.query) {
                    updateSearchQuery(event.query)
                    searchJob?.cancel()
                    searchJob = viewModelScope.launch {
                        searchAnime(event.query)
                    }
                }
            }

            is SearchEvent.OnSortToggled -> {
            }

            is SearchEvent.OnSearchInitiated -> {
                viewModelScope.launch {
                    searchAnime(searchQuery.value)
                }
            }

            is SearchEvent.ClearSearch -> {
                viewModelScope.launch {
                    clearSearch()
                }
            }
        }
    }

    private fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private fun clearSearch() {
        viewModelScope.launch { }
    }

    fun searchAnime(searchQuery: String) {
        if (searchQuery.isBlank()) return
        viewModelScope.launch {
            animePagingUseCase.invoke(
                searchQuery = searchQuery,
            ).cachedIn(viewModelScope).map {
                it.map { animeLightEntity ->
                    animeLightEntity.toAnimeLight()
                }
            }.collect {
                _anime.value = it
            }
        }
    }
}
