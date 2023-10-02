package com.ozcan.alasalvar.domain

import com.ozcan.alasalvar.common.result.Result
import com.ozcanalasalvar.testing.data.listOfCityTestData
import com.ozcanalasalvar.testing.data.weatherDetailTestData
import com.ozcanalasalvar.testing.repository.TestCityRepository
import com.ozcanalasalvar.testing.repository.TestWeatherRepository
import com.ozcanalasalvar.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetWeatherDetailUseCaseTest {


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var cityRepository: TestCityRepository
    private lateinit var weatherRepository: TestWeatherRepository
    private lateinit var useCase: GetWeatherDetailUseCase

    @Before
    fun setUp() {
        cityRepository = TestCityRepository()
        cityRepository.sendCities(listOfCityTestData)
        weatherRepository = TestWeatherRepository()
        weatherRepository.sendWeatherDetails(weatherDetailTestData)
        useCase =
            GetWeatherDetailUseCase(UnconfinedTestDispatcher(), cityRepository, weatherRepository)
    }

    @Test
    fun getWeatherDetailUseCase_fetch_detail_check_city_is_true() = runTest {

        val detail = useCase.invoke(listOfCityTestData[0].id).first()

        assertTrue { detail is Result.Success }
        assertEquals((detail as Result.Success).data.city, listOfCityTestData[0])

    }

    @Test
    fun getWeatherDetailUseCase_fetch_detail_has_fail() = runTest {

        val detail = useCase.invoke(12314).first()

        assertTrue { detail is Result.Error }

    }
}