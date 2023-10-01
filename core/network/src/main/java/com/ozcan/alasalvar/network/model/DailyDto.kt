package com.ozcan.alasalvar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyDto(
    val dt: Int,
    val temp: TempDto,
    val weather: List<WeatherInfoDto>,
)
