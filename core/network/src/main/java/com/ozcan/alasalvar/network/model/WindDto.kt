package com.ozcan.alasalvar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WindDto(
    val deg: Int,
    val speed: Double
)