package com.ozcan.alasalvar.search

import com.ozcan.alasalvar.model.data.City
import com.ozcanalasalvar.testing.repository.TestCityRepository
import com.ozcanalasalvar.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val cityRepository = TestCityRepository()
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        viewModel = SearchViewModel(cityRepository)
    }


    @Test
    fun searchViewModel_state_is_initially_loading() = runTest {
        assertEquals(viewModel.uiState.value , SearchUiState(emptyList(),false))
    }

    @Test
    fun searchViewModel_state_is_all_cities_with_empty_query() = runTest {
        cityRepository.sendCities(cities)
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        viewModel.onSearch("")


        assertEquals(viewModel.uiState.value.results.size,3)
        collectJob.cancel()
    }

    @Test
    fun searchViewModel_state_is_success_with_search_query() = runTest {
        cityRepository.sendCities(cities)
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        viewModel.onSearch("City1")

        assertEquals(viewModel.uiState.value.results.size,1)
        assertEquals(viewModel.uiState.value.noResult,false)
        collectJob.cancel()
    }

    @Test
    fun searchViewModel_state_is_empty_with_not_matching_search_query() = runTest {
        cityRepository.sendCities(cities)
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }

        viewModel.onSearch("Sample")

        assertEquals(viewModel.uiState.value , SearchUiState(emptyList(),true))
        collectJob.cancel()
    }


    private val cities: List<City> = mutableListOf(
        City(
            id = 1,
            country = "test1",
            lat = 12453.0,
            lon = 12453.0,
            name = "City1",
            isFavorite = true,
            isCurrentLocation = true,
        ),
        City(
            id = 2,
            country = "test2",
            lat = 12453.0,
            lon = 12453.0,
            name = "City2",
            isFavorite = false,
            isCurrentLocation = false,
        ),
        City(
            id = 3,
            country = "test3",
            lat = 12453.0,
            lon = 12453.0,
            name = "City3",
            isFavorite = false,
            isCurrentLocation = false,
        ),
    )
}