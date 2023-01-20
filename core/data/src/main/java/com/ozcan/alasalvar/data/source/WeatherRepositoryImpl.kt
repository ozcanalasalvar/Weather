package com.ozcan.alasalvar.data.source

import com.ozcan.alasalvar.data.WeatherRepository
import com.ozcan.alasalvar.network.WeatherDataSource
import com.ozcan.alasalvar.network.model.WeatherDetailDto
import com.ozcan.alasalvar.network.model.WeatherDto
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherDataSource: WeatherDataSource) :
    WeatherRepository {

    override suspend fun getWeatherData(cityName: String?, lat: Double?, lon: Double?): WeatherDto {
        return weatherDataSource.getWeatherData(cityName,lat,lon)
    }

    override suspend fun getWeatherDetail(lat: Double, lon: Double): WeatherDetailDto {
        return weatherDataSource.getWeatherDetail(lat, lon)
    }
}