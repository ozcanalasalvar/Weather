package com.ozcan.alasalvar.network.model

import com.ozcan.alasalvar.network.model.WeatherInfoDto

data class HourlyDto(
    val dt: Int,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val wind_speed: Double,
    val temp: Double,
    val weather: List<WeatherInfoDto>,
)