package com.ozcan.alasalvar.domain

import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Weather
import com.ozcanalasalvar.testing.repository.TestCityRepository
import com.ozcanalasalvar.testing.repository.TestWeatherRepository
import com.ozcanalasalvar.testing.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class GetCurrentWeatherUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var cityRepository: TestCityRepository
    private lateinit var weatherRepository: TestWeatherRepository
    private lateinit var useCase: GetCurrentWeatherUseCase

    @Before
    fun setUp() {
        cityRepository = TestCityRepository()
        cityRepository.sendCities(mutableListOf())
        weatherRepository = TestWeatherRepository()
        useCase = GetCurrentWeatherUseCase(cityRepository, weatherRepository)
    }

    @Test
    fun getCurrentWeatherUseCase_fetch_city_from_coordinate_current_location_is_true() = runTest {
        weatherRepository.sendWeathers(listOf(mockWeather))

        val result = useCase.invoke(latitude = 12453.0, longitude = 12453.0)

        assertTrue(result is Result.Success)
    }


    private val mockWeather = Weather(
        city = City(
            id = 28,
            country = "test28",
            lat = 12453.0,
            lon = 12453.0,
            name = "City28",
            isFavorite = false,
            isCurrentLocation = false,
        ),
        weatherIcon = null,
        weatherStatus = "cloudy",
        currentTemperature = "28°C",
        todayDate = "12/12/2028",
    )
}