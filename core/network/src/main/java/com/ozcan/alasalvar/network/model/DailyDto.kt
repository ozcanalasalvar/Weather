package com.ozcan.alasalvar.network.model

data class DailyDto(
    val clouds: Int,
    val dt: Int,
    val tempDto: TempDto,
    val weather: List<WeatherInfoDto>,
)