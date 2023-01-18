package com.ozcan.alasalvar.network.model

import com.ozcan.alasalvar.model.data.Hourly
import com.ozcan.alasalvar.network.util.asHour
import com.ozcan.alasalvar.network.util.asImageUrl
import com.ozcan.alasalvar.network.util.asTemperature


data class HourlyDto(
    val dt: Int,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val wind_speed: Double,
    val temp: Double,
    val weather: List<WeatherInfoDto>,
)


fun HourlyDto.asExternalModel(): Hourly = Hourly(
    temperature = temp.asTemperature(),
    icon = weather[0].icon.asImageUrl(),
    hour = dt.asHour(),
)
