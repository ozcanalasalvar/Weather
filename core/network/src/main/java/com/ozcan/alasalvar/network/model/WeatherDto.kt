package com.ozcan.alasalvar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    val base: String,
    val cloudsDto: CloudsDto,
    val cod: Int,
    val coordinateDto: CoordinateDto,
    val dt: Int,
    val id: Int,
    val mainDto: MainDto,
    val name: String,
    val sys: SysDto,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDetailDto>,
    val wind: WindDto
)