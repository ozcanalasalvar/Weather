package com.ozcan.alasalvar.detail

import com.ozcan.alasalvar.domain.GetWeatherDetailUseCase
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Daily
import com.ozcan.alasalvar.model.data.Hourly
import com.ozcan.alasalvar.model.data.WeatherDetail
import com.ozcanalasalvar.testing.repository.TestCityRepository
import com.ozcanalasalvar.testing.repository.TestWeatherRepository
import com.ozcanalasalvar.testing.util.MainDispatcherRule
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class DetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getWeatherDetailUseCase: GetWeatherDetailUseCase
    private lateinit var cityRepository: TestCityRepository
    private lateinit var weatherRepository: TestWeatherRepository
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        cityRepository = TestCityRepository()
        cityRepository.sendCities(listOf(city))
        weatherRepository = TestWeatherRepository()
        weatherRepository.sendWeatherDetails(listOf(detail))
        getWeatherDetailUseCase =
            GetWeatherDetailUseCase(UnconfinedTestDispatcher(), cityRepository, weatherRepository)
        viewModel = DetailViewModel(getWeatherDetailUseCase, cityRepository)
    }

    @Test
    fun detailViewModel_initial_state_loading() = runTest {
        assertEquals(viewModel.uiState.isLoading, true)
    }

    @Test
    fun detailViewModel_fetch_detail_state_returns_content() = runTest {
        assertEquals(viewModel.uiState.isLoading, true)
        viewModel.init(city.id)
        assertEquals(viewModel.uiState.data?.city?.id, city.id)
    }


    @Test
    fun detailViewModel_fetch_detail_state_returns_empty() = runTest {
        assertEquals(viewModel.uiState.isLoading, true)
        viewModel.init(1241)
        assertEquals(viewModel.uiState.data, null)
    }

    @Test
    fun detailViewModel_fetch_detail_state_returns_error() = runTest {
        cityRepository.sendThrowException(true)
        viewModel.init(21312)
        assertEquals(viewModel.uiState.error, "Test Exception")
    }


    val city = City(
        id = 1,
        country = "test1",
        lat = 12.0,
        lon = 12.0,
        name = "City1",
        isFavorite = true,
        isCurrentLocation = true,
    )

    val detail = WeatherDetail(
        city = city,
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
    )
}