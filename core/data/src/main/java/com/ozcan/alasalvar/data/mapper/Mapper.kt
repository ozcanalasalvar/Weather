package com.ozcan.alasalvar.data.mapper

import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Daily
import com.ozcan.alasalvar.model.data.Hourly
import com.ozcan.alasalvar.model.data.Weather
import com.ozcan.alasalvar.model.data.WeatherDetail
import com.ozcan.alasalvar.network.model.DailyDto
import com.ozcan.alasalvar.network.model.HourlyDto
import com.ozcan.alasalvar.network.model.WeatherDetailDto
import com.ozcan.alasalvar.network.model.WeatherDto

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


fun DailyDto.asExternalModel(): Daily = Daily(
    temperatureMin = temp.min.asTemperature(),
    temperatureMax = temp.max.asTemperature(),
    icon = weather[0].icon.asImageUrl(),
    weatherStatus = weather[0].main,
    date = dt.asDailyDate(),
)


fun WeatherDto.asExternalModel(): Weather {
    return Weather(
        city = City(
            id =id,
            name =name,
            country =sys.country,
            lat =coord.lat,
            lon =coord.lon,
            isFavorite =false,
        ),
        weatherIcon = weather[0].icon.asImageUrl(),
        weatherStatus = weather[0].main,
        currentTemperature = main.temp.asTemperature(),
        todayDate = dt.asDailyDate(),
    )
}


fun HourlyDto.asExternalModel(): Hourly = Hourly(
    temperature = temp.asTemperature() + "C",
    icon = weather[0].icon.asImageUrl(),
    hour = dt.asHour(),
)