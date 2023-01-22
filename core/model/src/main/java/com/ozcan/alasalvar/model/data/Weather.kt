package com.ozcan.alasalvar.model.data

data class Weather(
    val city: City,
    val weatherIcon: String,
    val weatherStatus: String,
    val currentTemperature: String,
    val todayDate: String,
)