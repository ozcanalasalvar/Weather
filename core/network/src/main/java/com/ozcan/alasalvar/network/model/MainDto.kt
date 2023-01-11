package com.ozcan.alasalvar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class MainDto(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)