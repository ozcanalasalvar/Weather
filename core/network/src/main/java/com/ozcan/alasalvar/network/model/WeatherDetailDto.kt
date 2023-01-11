package com.ozcan.alasalvar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetailDto(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)