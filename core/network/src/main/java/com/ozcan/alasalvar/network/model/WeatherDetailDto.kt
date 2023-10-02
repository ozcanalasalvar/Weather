package com.ozcan.alasalvar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetailDto(
    val current: HourlyDto,
    val daily: List<DailyDto>,
    val hourly: List<HourlyDto>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)