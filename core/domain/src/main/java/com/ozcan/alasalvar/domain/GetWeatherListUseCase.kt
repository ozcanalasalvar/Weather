package com.ozcan.alasalvar.domain

import com.ozcan.alasalvar.data.CityRepository
import com.ozcan.alasalvar.data.WeatherRepository
import com.ozcan.alasalvar.model.data.Weather
import com.ozcan.alasalvar.model.data.WeatherDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherListUseCase @Inject constructor(
    cityRepository: CityRepository,
    weatherRepository: WeatherRepository
) {


//    operator fun invoke(): Flow<Weather> {
//
//    }
}