package com.ozcan.alasalvar.search

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.common.result.asResult
import com.ozcan.alasalvar.data.CityRepository
import com.ozcan.alasalvar.model.data.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class SearchUiState(
    val results: List<City> = emptyList(),
    val noResult: Boolean = true
)

@HiltViewModel
class SearchViewModel @Inject constructor(private val cityRepository: CityRepository) :
    ViewModel() {

    private val query = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _uiState = query.flatMapLatest {
        searchCities(it)
    }

    val uiState: StateFlow<SearchUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchUiState(results = emptyList())
    )

    private fun searchCities(cityQuery: String): Flow<SearchUiState> {
        return cityRepository.getCities()
            .asResult().map {
                when (it) {
                    is Result.Error -> {
                        SearchUiState(results = emptyList())
                    }
                    Result.Loading -> {
                        SearchUiState(results = emptyList())
                    }
                    is Result.Success -> {
                        it.data.filter { city -> city.name.contains(cityQuery) }.let { list ->
                            SearchUiState(results = list)
                        }
                    }
                }
            }
    }

    fun onSearch(searchQuery: String) {
        query.value = searchQuery
    }
}