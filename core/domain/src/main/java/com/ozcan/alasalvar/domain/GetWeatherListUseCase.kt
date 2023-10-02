package com.ozcan.alasalvar.domain

import com.ozcan.alasalvar.common.dispatcher.AppDispatchers
import com.ozcan.alasalvar.common.dispatcher.Dispatcher
import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.data.repository.WeatherRepository
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Weather
import kotlinx.coroutines.*
import javax.inject.Inject

class GetWeatherListUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(cities: List<City>): Result<List<Weather>> {
        try {
            val deferredList = mutableListOf<Deferred<Weather>>()
            val list = mutableListOf<Weather>()


            val result = withContext(ioDispatcher) {
                cities.forEach {
                    val weatherDto = async {
                        weatherRepository.getWeatherData(cityName = it.name)
                    }
                    deferredList.add(weatherDto)
                }
                try {
                    deferredList.mapNotNull { deferredResult ->

                        deferredResult.await()

                    }.forEach {
                        list.add(it)
                    }

                    return@withContext Result.Success(list)
                } catch (exception: Exception) {
                    return@withContext Result.Error(exception)
                }
            }
            return result
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}