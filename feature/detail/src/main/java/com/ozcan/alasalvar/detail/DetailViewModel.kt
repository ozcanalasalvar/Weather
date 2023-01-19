package com.ozcan.alasalvar.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozcan.alasalvar.domain.GetWeatherDetailUseCase
import com.ozcan.alasalvar.model.data.WeatherDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val data: WeatherDetail? = null
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
        detailUseCase.invoke(2852362).collectLatest {
            uiState = uiState.copy(data = it)
        }
    }
}