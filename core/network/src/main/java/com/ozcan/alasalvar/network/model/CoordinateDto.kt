package com.ozcan.alasalvar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CoordinateDto(
    val lat: Double,
    val lon: Double
)