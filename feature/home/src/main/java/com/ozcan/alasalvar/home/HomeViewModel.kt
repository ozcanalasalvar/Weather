package com.ozcan.alasalvar.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.data.repository.CityRepository
import com.ozcan.alasalvar.data.util.LocationTracker
import com.ozcan.alasalvar.domain.GetCurrentWeatherUseCase
import com.ozcan.alasalvar.domain.GetWeatherListUseCase
import com.ozcan.alasalvar.model.data.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true, val favorites: List<Weather>? = null, val error: String? = null
)

data class CurrentLocationUiState(
    val current: Weather? = null
)


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val locationTracker: LocationTracker,
    private val getWeatherListUseCase: GetWeatherListUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = weatherUiState().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )

    private var _locationUiState: MutableStateFlow<CurrentLocationUiState> =
        MutableStateFlow(CurrentLocationUiState())

    var locationUiState: StateFlow<CurrentLocationUiState> = _locationUiState

    private fun weatherUiState(): Flow<HomeUiState> {

        return cityRepository.getFavoriteCities().distinctUntilChanged { old, new ->
            old.size == new.size
        }.map {

            when (val result = getWeatherListUseCase.invoke(it)) {
                is Result.Error -> {

                    HomeUiState(
                        isLoading = true, favorites = null, error = result.exception.toString()
                    )

                }

                Result.Loading -> {
                    HomeUiState(isLoading = true, favorites = null, error = null)
                }

                is Result.Success -> {
                    HomeUiState(
                        isLoading = false, favorites = result.data, error = null
                    )

                }
            }
        }

    }

    fun startLocationTrack() = viewModelScope.launch {
        locationTracker.getCurrentLocation().distinctUntilChanged { old, new ->
            old?.latitude == new?.latitude && old?.longitude == new?.longitude
        }.collectLatest { location ->
            when (val result = location?.let { getCurrentWeatherUseCase.invoke(it) }) {
                is Result.Success -> {
                    _locationUiState.value = CurrentLocationUiState(current = result.data)
                }

                else -> {
                    _locationUiState.value = CurrentLocationUiState(current = null)
                }
            }
        }

    }

}


