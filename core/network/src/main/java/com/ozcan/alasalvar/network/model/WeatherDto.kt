package com.ozcan.alasalvar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    val id: Int,
    val name: String,
    val coordinateDto: CoordinateDto,
    val dt: Int,
    val mainDto: MainDto,
    val timezone: Int,
    val weather: List<WeatherInfoDto>,
)


@Serializable
data class CoordinateDto(
    val lat: Double,
    val lon: Double
)


@Serializable
data class MainDto(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)