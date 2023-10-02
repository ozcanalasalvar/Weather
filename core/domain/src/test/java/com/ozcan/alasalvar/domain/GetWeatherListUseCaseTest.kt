package com.ozcan.alasalvar.domain

import com.ozcan.alasalvar.common.result.Result
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Weather
import com.ozcanalasalvar.testing.data.listOfCityTestData
import com.ozcanalasalvar.testing.data.listOfWeatherTestData
import com.ozcanalasalvar.testing.repository.TestWeatherRepository
import com.ozcanalasalvar.testing.util.MainDispatcherRule
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetWeatherListUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var weatherRepository: TestWeatherRepository
    private lateinit var useCase: GetWeatherListUseCase

    @Before
    fun setUp() {
        weatherRepository = TestWeatherRepository()
        weatherRepository.sendWeathers(
            listOf(
                Weather(
                    city = listOfCityTestData[0],
                    weatherIcon = null,
                    weatherStatus = "cloudy",
                    currentTemperature = "28°C",
                    todayDate = "12/12/2028",
                )
            )
        )
        useCase = GetWeatherListUseCase(weatherRepository, UnconfinedTestDispatcher())
    }

    @Test
    fun getWeatherListUseCase_fetch_weathers_of_cities_return_success() = runTest {
        val cities = listOf<City>(listOfCityTestData[0])

        val result = useCase.invoke(cities)
        assertTrue { result is Result.Success }
        assertEquals((result as Result.Success).data.size, 1)

    }

}