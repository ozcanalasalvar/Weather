package com.ozcanalasalvar.testing.repository

import com.ozcan.alasalvar.data.repository.WeatherRepository
import com.ozcan.alasalvar.model.data.Weather
import com.ozcan.alasalvar.model.data.WeatherDetail
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestWeatherRepository : WeatherRepository {

    private val weatherDetailFlow: MutableSharedFlow<List<WeatherDetail>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val weatherFlow: MutableSharedFlow<List<Weather>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private var weatherList = mutableListOf<Weather>()
    private var weatherDetailList = mutableListOf<WeatherDetail>()

    private var isExceptionEnabled = false

    override suspend fun getWeatherData(cityName: String?, lat: Double?, lon: Double?): Weather {
        if (isExceptionEnabled) {
            throw Exception("Test Exception")
        }
        return if (!cityName.isNullOrBlank()) {
            weatherList.find {
                it.city.name == cityName
            }!!
        } else {

            weatherList.find {
                it.city.lat == lat && it.city.lon == lon
            }!!

        }

    }

    override suspend fun getWeatherDetail(lat: Double, lon: Double): WeatherDetail {
        if (isExceptionEnabled) {
            throw Exception("Test Exception")
        }

        return weatherDetailList.find { it.city?.lat == lat && it.city?.lon == lon }!!
    }


    /**
     * Test only methods to add the weather to the stored list in memory
     */
    fun sendWeathers(weathers: List<Weather>) {
        weatherList = mutableListOf()
        weatherList.addAll(weathers)
        weatherFlow.tryEmit(weathers)
    }

    fun sendWeatherDetails(weatherDetails: List<WeatherDetail>) {
        weatherDetailList = mutableListOf()
        weatherDetailList.addAll(weatherDetails)
        weatherDetailFlow.tryEmit(weatherDetails)
    }

    fun sendThrowException(isExceptionEnabled: Boolean) {
        this.isExceptionEnabled = isExceptionEnabled
    }
}