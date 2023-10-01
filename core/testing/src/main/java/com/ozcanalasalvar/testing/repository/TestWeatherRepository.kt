package com.ozcanalasalvar.testing.repository

import com.ozcan.alasalvar.data.WeatherRepository
import com.ozcan.alasalvar.model.data.Weather
import com.ozcan.alasalvar.model.data.WeatherDetail

class TestWeatherRepository : WeatherRepository {

    private lateinit var weather: Weather
    private lateinit var weatherDetail: WeatherDetail

    override suspend fun getWeatherData(cityName: String?, lat: Double?, lon: Double?): Weather {
        return weather
    }

    override suspend fun getWeatherDetail(lat: Double, lon: Double): WeatherDetail {
        return weatherDetail
    }


    /**
     * Test only methods to add the weather to the stored list in memory
     */
    fun sendWeather(weather: Weather) {

    }

    fun sendWeatherDetail(weather: WeatherDetail) {

    }
}