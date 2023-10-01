package com.ozcan.alasalvar.network.model

import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.WeatherDetail
import com.ozcan.alasalvar.network.util.asCurrentDate
import com.ozcan.alasalvar.network.util.asImageUrl
import com.ozcan.alasalvar.network.util.asTemperature
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetailDto(
    val current: HourlyDto,
    val daily: List<DailyDto>,
    val hourly: List<HourlyDto>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)