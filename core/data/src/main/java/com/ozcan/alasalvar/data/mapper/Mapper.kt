package com.ozcan.alasalvar.data.mapper

import com.ozcan.alasalvar.model.data.WeatherDetail
import com.ozcan.alasalvar.network.model.WeatherDetailDto
import com.ozcan.alasalvar.network.model.asExternalModel
import com.ozcan.alasalvar.network.util.asCurrentDate
import com.ozcan.alasalvar.network.util.asImageUrl
import com.ozcan.alasalvar.network.util.asTemperature

fun WeatherDetailDto.asExternalModel(): WeatherDetail {
    return WeatherDetail(
        city = null,
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