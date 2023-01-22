package com.ozcan.alasalvar.domain

import android.location.Location
import com.ozcan.alasalvar.common.dispatcher.AppDispatchers
import com.ozcan.alasalvar.common.dispatcher.Dispatcher
import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.data.CityRepository
import com.ozcan.alasalvar.data.WeatherRepository
import com.ozcan.alasalvar.model.data.Weather
import com.ozcan.alasalvar.network.model.asExternalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(location: Location):Result<Weather> {
        try {
            val response = weatherRepository.getWeatherData(
                lat = location.latitude,
                lon = location.longitude
            ).asExternalModel()

            val city = response.city.copy(isFavorite = true, isCurrentLocation = true)
            cityRepository.updateStation(city)

           return Result.Success(response)
        } catch (e: Exception) {
           return Result.Error(e)
        }
    }
}