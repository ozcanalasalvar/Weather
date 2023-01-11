package com.ozcan.alasalvar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SysDto(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)