package com.ozcan.alasalvar.domain

import com.ozcan.alasalvar.common.dispatcher.AppDispatchers
import com.ozcan.alasalvar.common.dispatcher.Dispatcher
import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.data.repository.CityRepository
import com.ozcan.alasalvar.data.repository.WeatherRepository
import com.ozcan.alasalvar.model.data.WeatherDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetWeatherDetailUseCase @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(cityId: Int): Flow<Result<WeatherDetail>> = flow {
        val response = try {
            val city = cityRepository.getCity(cityId)
            val response = weatherRepository.getWeatherDetail(city.lat, city.lon)
            // throw Exception()
            Result.Success(response.copy(city = city))
        } catch (e: Exception) {
            Result.Error(e)
        }
        emit(response)

    }.flowOn(ioDispatcher)
}