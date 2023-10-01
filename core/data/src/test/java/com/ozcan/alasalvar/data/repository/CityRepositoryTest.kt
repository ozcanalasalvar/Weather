package com.ozcan.alasalvar.data.repository

import com.ozcan.alasalvar.model.data.City
import com.ozcanalasalvar.testing.data.listOfCityTestData
import com.ozcanalasalvar.testing.datasource.TestCityDataSource
import com.ozcanalasalvar.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CityRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var cityRepository: CityRepository
    private lateinit var cityDataSource: TestCityDataSource

    @Before
    fun setUp() {
        cityDataSource = TestCityDataSource()
        cityDataSource.sendCities(listOfCityTestData)
        cityDataSource.sendCurrentCity(listOfCityTestData[0])
        cityRepository = CityRepositoryImpl(UnconfinedTestDispatcher(), cityDataSource)
    }


    @Test
    fun cityRepository_fetch_all_cities_is_not_empty() = runTest {
        assertTrue { cityRepository.getCities().first().isNotEmpty() }
    }


    @Test
    fun cityRepository_update_city_return_true() = runTest {
        val city = City(
            id = 2,
            country = "test2",
            lat = 12453.0,
            lon = 12453.0,
            name = "test passed",
            isFavorite = false,
            isCurrentLocation = false,
        )
        cityRepository.updateCity(city)

        assertEquals(
            cityRepository.getCities().first().find { it.id == city.id }?.name, "test passed"
        )
    }


    @Test
    fun cityRepository_get_city_returns_true() = runTest {
        assertEquals(cityRepository.getCity(2).id, 2)
    }

    @Test
    fun cityRepository_get_favorite_city_returns_1() = runTest {
        assertEquals(cityRepository.getFavoriteCities().first().size, 1)
    }

}