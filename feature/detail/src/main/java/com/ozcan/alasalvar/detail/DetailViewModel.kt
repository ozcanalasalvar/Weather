package com.ozcan.alasalvar.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.data.repository.CityRepository
import com.ozcan.alasalvar.domain.GetWeatherDetailUseCase
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.WeatherDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val isLoading: Boolean = true,
    val data: WeatherDetail? = null,
    val error: String? = null,
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: GetWeatherDetailUseCase,
    private val cityRepository: CityRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    var uiState by mutableStateOf(DetailUiState())
        private set


    fun init(cityId: Int) {
        getDetail(cityId = cityId)
    }

    private fun getDetail(cityId: Int) = viewModelScope.launch {
        detailUseCase.invoke(cityId).collectLatest { result ->
            uiState = when (result) {
                is Result.Error -> {
                    uiState.copy(
                        data = null,
                        error = result.exception.toString(),
                        isLoading = false
                    )
                }
                Result.Loading -> {
                    uiState.copy(
                        data = null,
                        error = null,
                        isLoading = true
                    )
                }
                is Result.Success -> {
                    uiState.copy(
                        data = result.data,
                        error = null,
                        isLoading = false
                    )
                }
            }
        }
    }


    fun onFavoriteClick(city: City?) = viewModelScope.launch {
        city?.let {
            val _city = city.copy(isFavorite = !it.isFavorite)
            cityRepository.updateStation(_city)
            getDetail(cityId = city.id)
        }
    }
}