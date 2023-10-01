package com.ozcan.alasalvar.data

import com.ozcan.alasalvar.model.data.Weather
import com.ozcan.alasalvar.model.data.WeatherDetail

interface WeatherRepository {
    suspend fun getWeatherData(
        cityName: String? = null,
        lat: Double? = null,
        lon: Double? = null,
    ): Weather

    suspend fun getWeatherDetail(lat: Double, lon: Double): WeatherDetail
}