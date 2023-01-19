package com.ozcan.alasalvar.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.domain.GetWeatherDetailUseCase
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
    private val detailUseCase: GetWeatherDetailUseCase
) : ViewModel() {


    var uiState by mutableStateOf(DetailUiState())
        private set

    init {
        getDetail()
    }

    private fun getDetail() = viewModelScope.launch {
        detailUseCase.invoke(6359304).collectLatest { result ->
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
}