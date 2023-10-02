package com.ozcanalasalvar.testing.data

import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Weather


val listOfWeatherTestData: List<Weather> = mutableListOf(
    Weather(
        city = listOfCityTestData[0],
        weatherIcon = null,
        weatherStatus = "cloudy",
        currentTemperature = "28°C",
        todayDate = "12/12/2028",
    ),
    Weather(
        city = listOfCityTestData[1],
        weatherIcon = null,
        weatherStatus = "sunny",
        currentTemperature = "28°C",
        todayDate = "12/12/2028",
    ),
    Weather(
        city = listOfCityTestData[2],
        weatherIcon = null,
        weatherStatus = "cloudy",
        currentTemperature = "28°C",
        todayDate = "12/12/2028",
    ),
    Weather(
        city = listOfCityTestData[3],
        weatherIcon = null,
        weatherStatus = "cloudy",
        currentTemperature = "28°C",
        todayDate = "12/12/2028",
    ),

    )