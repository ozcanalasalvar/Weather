package com.ozcan.alasalvar.home


import com.ozcan.alasalvar.domain.GetCurrentWeatherUseCase
import com.ozcan.alasalvar.domain.GetWeatherListUseCase
import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Weather
import com.ozcanalasalvar.testing.locationtracker.TestLocationTracker
import com.ozcanalasalvar.testing.repository.TestCityRepository
import com.ozcanalasalvar.testing.repository.TestWeatherRepository
import com.ozcanalasalvar.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var cityRepository: TestCityRepository
    private lateinit var weatherRepository: TestWeatherRepository

    private val locationTracker = TestLocationTracker()
    private lateinit var getWeatherListUseCase: GetWeatherListUseCase
    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        cityRepository = TestCityRepository()
        weatherRepository = TestWeatherRepository()
        cityRepository.sendCities(cities)
        weatherRepository.sendWeathers(weathers)
        getWeatherListUseCase = GetWeatherListUseCase(weatherRepository, UnconfinedTestDispatcher())
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(cityRepository, weatherRepository)

        viewModel = HomeViewModel(
            cityRepository = cityRepository,
            locationTracker = locationTracker,
            getWeatherListUseCase = getWeatherListUseCase,
            getCurrentWeatherUseCase = getCurrentWeatherUseCase,
        )
    }


    @Test
    fun homeViewModel_trackLocation_returns_location() = runTest {

        locationTracker.sendLocation(cities[0])
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.locationUiState.collect() }
        viewModel.startLocationTrack()

        assertEquals(viewModel.locationUiState.value.current?.city?.name, cities[0].name)
        assertEquals(
            viewModel.locationUiState.value.current?.currentTemperature,
            weathers[0].currentTemperature
        )


        collectJob.cancel()
    }

    @Test
    fun homeViewModel_trackLocation_returns__no_location_result() = runTest {

        locationTracker.sendLocation(null)
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.locationUiState.collect() }
        viewModel.startLocationTrack()

        assertEquals(viewModel.locationUiState.value.current, null)

        collectJob.cancel()
    }

    @Test
    fun homeViewModel_fetch_favorite_cities_weather_returns_result() = runTest {
        cityRepository.sendCities(
            mutableListOf(
                City(
                    id = 1,
                    country = "test1",
                    lat = 12.0,
                    lon = 12.0,
                    name = "City1",
                    isFavorite = false,
                    isCurrentLocation = true,
                )
            )
        )
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(cityRepository, weatherRepository)
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        assertEquals(viewModel.uiState.value.favorites?.size, 1)
        collectJob.cancel()
    }

    @Test
    fun homeViewModel_fetch_favorite_cities_weather_returns_no_result() = runTest {

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        assertEquals(viewModel.uiState.value.favorites?.size, 1)
        collectJob.cancel()
    }




    private val cities: List<City> = mutableListOf(
        City(
            id = 1,
            country = "test1",
            lat = 12.0,
            lon = 12.0,
            name = "City1",
            isFavorite = true,
            isCurrentLocation = true,
        ),
        City(
            id = 2,
            country = "test2",
            lat = 1245.0,
            lon = 1245.0,
            name = "City2",
            isFavorite = false,
            isCurrentLocation = false,
        ),
        City(
            id = 3,
            country = "test3",
            lat = 124.0,
            lon = 124.0,
            name = "City3",
            isFavorite = false,
            isCurrentLocation = false,
        ),
    )


    val weathers: List<Weather> = mutableListOf(
        Weather(
            city = cities[0],
            weatherIcon = null,
            weatherStatus = "cloudy",
            currentTemperature = "28°C",
            todayDate = "12/12/2028",
        ),
        Weather(
            city = cities[1],
            weatherIcon = null,
            weatherStatus = "sunny",
            currentTemperature = "28°C",
            todayDate = "12/12/2028",
        ),
        Weather(
            city = cities[2],
            weatherIcon = null,
            weatherStatus = "cloudy",
            currentTemperature = "28°C",
            todayDate = "12/12/2028",
        ),
    )

}