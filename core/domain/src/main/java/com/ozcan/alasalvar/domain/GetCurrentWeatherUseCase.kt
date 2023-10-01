package com.ozcan.alasalvar.domain

import android.location.Location
import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.data.repository.CityRepository
import com.ozcan.alasalvar.data.repository.WeatherRepository
import com.ozcan.alasalvar.model.data.Weather
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository,
) {
    suspend operator fun invoke(location: Location): Result<Weather> {
        try {
            val response = weatherRepository.getWeatherData(
                lat = location.latitude,
                lon = location.longitude
            )

            val city = response.city.copy(isFavorite = true, isCurrentLocation = true)
            cityRepository.updateCity(city)

            return Result.Success(response)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}