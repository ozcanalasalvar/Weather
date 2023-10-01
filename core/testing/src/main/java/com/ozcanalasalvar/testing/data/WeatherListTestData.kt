package com.ozcanalasalvar.testing.data

import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Weather


val listOfWeatherTestData: List<Weather> = mutableListOf(
    Weather(
        city = City(
            id = 1,
            country = "test",
            lat = 123.0,
            lon = 123.0,
            name = "Istanbul",
            isFavorite = true,
            isCurrentLocation = false,
        ),
        weatherIcon = null,
        weatherStatus = "cloudy",
        currentTemperature = "28°C",
        todayDate = "12/12/2028",
    ),
    Weather(
        city = City(
            id = 2,
            country = "test2",
            lat = 1233.0,
            lon = 1233.0,
            name = "Berlin",
            isFavorite = true,
            isCurrentLocation = false,
        ),
        weatherIcon = null,
        weatherStatus = "sunny",
        currentTemperature = "28°C",
        todayDate = "12/12/2028",
    ),
    Weather(
        city = City(
            id = 3,
            country = "test3",
            lat = 1243.0,
            lon = 1243.0,
            name = "Paris",
            isFavorite = false,
            isCurrentLocation = false,
        ),
        weatherIcon = null,
        weatherStatus = "cloudy",
        currentTemperature = "28°C",
        todayDate = "12/12/2028",
    ),
    Weather(
        city = City(
            id = 4,
            country = "test4",
            lat = 12453.0,
            lon = 12453.0,
            name = "Giresun",
            isFavorite = false,
            isCurrentLocation = false,
        ),
        weatherIcon = null,
        weatherStatus = "cloudy",
        currentTemperature = "28°C",
        todayDate = "12/12/2028",
    ),

    )