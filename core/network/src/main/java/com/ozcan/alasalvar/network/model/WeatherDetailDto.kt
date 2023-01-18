package com.ozcan.alasalvar.network.model

data class WeatherDetailDto(
    val hourlyDto: HourlyDto,
    val dailyDto: List<DailyDto>,
    val hourly: List<HourlyDto>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)