package com.ozcan.alasalvar.data

import com.ozcan.alasalvar.network.model.WeatherDetailDto
import com.ozcan.alasalvar.network.model.WeatherDto

interface WeatherRepository {
    suspend fun getWeatherData(
        cityName: String? = null,
        lat: Double? = null,
        lon: Double? = null,
    ): WeatherDto

    suspend fun getWeatherDetail(lat: Double, lon: Double): WeatherDetailDto
}