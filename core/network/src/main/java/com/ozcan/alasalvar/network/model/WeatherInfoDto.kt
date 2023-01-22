package com.ozcan.alasalvar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfoDto(
    val id: Int,
    val main: String,
    val icon: String,
    val description: String,
)