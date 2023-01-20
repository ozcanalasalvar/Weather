package com.ozcan.alasalvar.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozcan.alasalvar.data.CityRepository
import com.ozcan.alasalvar.data.util.LocationTracker
import com.ozcan.alasalvar.model.data.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val current: Weather? = null,
    val favorites: List<Weather> = emptyList(),
    val error: String? = null
)


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val locationTracker: LocationTracker,
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeUiState())

//    _uiState.update { it.copy() }
    val uiState: StateFlow<HomeUiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )

    init {
        fetchCities()
        getCurrentLocation()
    }


    private fun fetchCities() = viewModelScope.launch {
        cityRepository.getCities()
            .map { city -> city.filter { it.isFavorite } }
            .collectLatest {
                val list = it

            }
    }

    private fun getCurrentLocation() = viewModelScope.launch {
        locationTracker.getCurrentLocation()
            .distinctUntilChanged { old, new ->
                old?.latitude == new?.latitude && old?.longitude == new?.longitude
            }
            .collectLatest {
                val location = it
            }
    }
}