package com.ozcan.alasalvar.model.data

data class WeatherDetail(
    val city: City,
    val weatherIcon: String,
    val weatherStatus: String,
    val currentTemperature: String,
    val todayDate: String,
    val wind: String,
    val humidity: String,
    val pressure: String,
    val hourlyWeather: List<Hourly>,
    val dailyWeather: List<Daily>,
)

data class Hourly(
    val temperature: String,
    val icon: String,
    val hour: String,
)

data class Daily(
    val temperatureMin: String,
    val temperatureMax: String,
    val icon: String,
    val weatherStatus: String,
    val date: String,
)