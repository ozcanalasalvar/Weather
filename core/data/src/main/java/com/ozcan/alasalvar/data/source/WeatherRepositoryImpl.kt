package com.ozcan.alasalvar.data.source

import com.ozcan.alasalvar.data.WeatherRepository
import com.ozcan.alasalvar.data.mapper.asExternalModel
import com.ozcan.alasalvar.model.data.Weather
import com.ozcan.alasalvar.model.data.WeatherDetail
import com.ozcan.alasalvar.network.WeatherDataSource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherDataSource: WeatherDataSource) :
    WeatherRepository {

    override suspend fun getWeatherData(cityName: String?, lat: Double?, lon: Double?): Weather {
        return weatherDataSource.getWeatherData(cityName, lat, lon).asExternalModel()
    }

    override suspend fun getWeatherDetail(lat: Double, lon: Double): WeatherDetail {
        return weatherDataSource.getWeatherDetail(lat, lon).asExternalModel()
    }
}