package com.ozcan.alasalvar.network.model

import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.WeatherDetail
import com.ozcan.alasalvar.network.util.asCurrentDate
import com.ozcan.alasalvar.network.util.asImageUrl
import com.ozcan.alasalvar.network.util.asTemperature

data class WeatherDetailDto(
    val current: HourlyDto,
    val daily: List<DailyDto>,
    val hourly: List<HourlyDto>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)

fun WeatherDetailDto.toExternalModel(city: City): WeatherDetail {
    return WeatherDetail(
        city = city,
        weatherIcon = current.weather[0].icon.asImageUrl(),
        weatherStatus = current.weather[0].main,
        currentTemperature = current.temp.asTemperature(),
        todayDate = current.dt.asCurrentDate(),
        wind = "${current.wind_speed} km/h",
        humidity = "${current.humidity} %",
        pressure = "${current.pressure} Pa",
        hourlyWeather = hourly.map { value -> value.asExternalModel() },
        dailyWeather = daily.map { value -> value.asExternalModel() },
    )
}