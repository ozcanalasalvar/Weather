package com.ozcan.alasalvar.domain

import com.ozcan.alasalvar.common.dispatcher.AppDispatchers
import com.ozcan.alasalvar.common.dispatcher.Dispatcher
import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.data.WeatherRepository
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Weather
import com.ozcan.alasalvar.network.model.asExternalModel
import kotlinx.coroutines.*
import javax.inject.Inject

class GetWeatherListUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(cities: List<City>): Result<List<Weather>> {
        val deferredList = mutableListOf<Deferred<Weather>>()
        val list = mutableListOf<Weather>()

        withContext(ioDispatcher) {
            cities.forEach {
                val weatherDto = async {
                    weatherRepository.getWeatherData(cityName = it.name)
                }
                deferredList.add(weatherDto)
            }

            deferredList.mapNotNull { deferredResult ->
                try {
                    deferredResult.await()
                } catch (exception: Exception) {
                    null
                }
            }.forEach {
                list.add(it)
            }
        }

        return Result.Success(list)
    }
}