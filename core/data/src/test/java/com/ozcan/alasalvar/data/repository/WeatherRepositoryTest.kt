package com.ozcan.alasalvar.data.repository

import com.ozcan.alasalvar.model.data.Weather
import com.ozcan.alasalvar.model.data.WeatherDetail
import com.ozcanalasalvar.testing.datasource.TestWeatherDataSource
import com.ozcanalasalvar.testing.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class WeatherRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        weatherRepository = WeatherRepositoryImpl(TestWeatherDataSource())
    }


    @Test
    fun weatherRepository_fetch_weather_data_maps_to_weather() = runTest {
        assertTrue { weatherRepository.getWeatherData(null, 123.4, 123.4) is Weather }
    }

    @Test
    fun weatherRepository_fetch_weather_detail_data_maps_to_weather_detail() = runTest {
        assertTrue { weatherRepository.getWeatherDetail(123.4, 123.4) is WeatherDetail }

        assertTrue {
            weatherRepository.getWeatherDetail(
                123.4,
                123.4
            ).weatherIcon == "https://openweathermap.org/img/wn/3n@2x.png"
        }

    }
}