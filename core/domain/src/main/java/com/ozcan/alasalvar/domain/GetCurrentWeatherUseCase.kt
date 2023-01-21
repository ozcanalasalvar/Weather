package com.ozcan.alasalvar.domain

import android.location.Location
import com.ozcan.alasalvar.common.dispatcher.AppDispatchers
import com.ozcan.alasalvar.common.dispatcher.Dispatcher
import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.data.WeatherRepository
import com.ozcan.alasalvar.model.data.Weather
import com.ozcan.alasalvar.network.model.asExternalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(location: Location): Flow<Result<Weather>> = flow {
        try {
            emit(
                Result.Success(
                    weatherRepository.getWeatherData(
                        lat = location.latitude,
                        lon = location.longitude
                    ).asExternalModel()
                )
            )
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }.flowOn(ioDispatcher)
}