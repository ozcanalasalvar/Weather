package com.ozcanalasalvar.testing.data

import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Daily
import com.ozcan.alasalvar.model.data.Hourly
import com.ozcan.alasalvar.model.data.WeatherDetail


val weatherDetailTestData: List<WeatherDetail> = listOf<WeatherDetail>(
    WeatherDetail(
        city = City(
            id = 1,
            country = "test1",
            lat = 12453.0,
            lon = 12453.0,
            name = "City1",
            isFavorite = false,
            isCurrentLocation = false,
        ),
        weatherIcon = "",
        weatherStatus = "Cloudy",
        currentTemperature = "28°C",
        todayDate = "12/12/12",
        wind = "123",
        humidity = "123",
        pressure = "123",
        hourlyWeather = listOf(
            Hourly(
                temperature = "28°C",
                icon = "",
                hour = "12:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "13:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "14:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "15:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "16:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "17:00",
            )
        ),
        dailyWeather = listOf(
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "12/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "13/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "12/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "14/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "15/12/2022",
            ),
        ),
    ),
    WeatherDetail(
        city = City(
            id = 2,
            country = "test2",
            lat = 12453.0,
            lon = 12453.0,
            name = "City1",
            isFavorite = false,
            isCurrentLocation = false,
        ),
        weatherIcon = "",
        weatherStatus = "Cloudy",
        currentTemperature = "28°C",
        todayDate = "12/12/12",
        wind = "123",
        humidity = "123",
        pressure = "123",
        hourlyWeather = listOf(
            Hourly(
                temperature = "28°C",
                icon = "",
                hour = "12:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "13:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "14:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "15:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "16:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "17:00",
            )
        ),
        dailyWeather = listOf(
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "12/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "13/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "12/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "14/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "15/12/2022",
            ),
        ),
    ),
    WeatherDetail(
        city = City(
            id = 3,
            country = "test3",
            lat = 12453.0,
            lon = 12453.0,
            name = "City1",
            isFavorite = false,
            isCurrentLocation = false,
        ),
        weatherIcon = "",
        weatherStatus = "Cloudy",
        currentTemperature = "28°C",
        todayDate = "12/12/12",
        wind = "123",
        humidity = "123",
        pressure = "123",
        hourlyWeather = listOf(
            Hourly(
                temperature = "28°C",
                icon = "",
                hour = "12:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "13:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "14:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "15:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "16:00",
            ), Hourly(
                temperature = "28°C",
                icon = "",
                hour = "17:00",
            )
        ),
        dailyWeather = listOf(
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "12/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "13/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "12/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "14/12/2022",
            ),
            Daily(
                temperatureMin = "28°C",
                temperatureMax = "35°C",
                icon = "",
                weatherStatus = "cloudy",
                date = "15/12/2022",
            ),
        ),
    ),
)