package com.ozcan.alasalvar.home

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.data.CityRepository
import com.ozcan.alasalvar.data.util.LocationTracker
import com.ozcan.alasalvar.domain.GetCurrentWeatherUseCase
import com.ozcan.alasalvar.domain.GetWeatherListUseCase
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val current: Weather? = null,
    val favorites: List<Weather>? = null,
    val error: String? = null
)


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val locationTracker: LocationTracker,
    private val getWeatherListUseCase: GetWeatherListUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeUiState())

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
        cityRepository.getFavoriteCities()
            .map { city -> city.filter { !it.isFavorite } }
            .collectLatest {
                getWeatherList(it)
            }
    }

    private fun getCurrentLocation() = viewModelScope.launch {
        locationTracker.getCurrentLocation()
            .distinctUntilChanged { old, new ->
                old?.latitude == new?.latitude && old?.longitude == new?.longitude
            }
            .collectLatest { location ->
                location?.let { getCurrentWeather(it) }
            }
    }


    private fun getWeatherList(cities: List<City>) = viewModelScope.launch {
        getWeatherListUseCase.invoke(cities).collectLatest { result ->
            when (result) {
                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = true,
                            favorites = null,
                            error = result.exception.toString()
                        )
                    }
                }
                Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true, favorites = null, error = null) }
                }
                is Result.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            favorites = result.data,
                            error = null
                        )
                    }
                }
            }
        }
    }

    private fun getCurrentWeather(location: Location) = viewModelScope.launch {
        getCurrentWeatherUseCase.invoke(location).collectLatest { result ->
            when (result) {
                is Result.Error -> {}
                Result.Loading -> {}
                is Result.Success -> {
                    _uiState.update { it.copy(current = result.data) }
                }
            }
        }
    }
}