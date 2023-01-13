package com.ozcan.alasalvar.model.data

data class City(
    val id: Int,
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val isFavorite: Boolean = false
)