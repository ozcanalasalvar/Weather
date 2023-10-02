package com.ozcanalasalvar.testing.repository

import com.ozcan.alasalvar.data.repository.CityRepository
import com.ozcan.alasalvar.model.data.City
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TestCityRepository : CityRepository {


    private val cityFlow: MutableSharedFlow<List<City>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private var cityList = mutableListOf<City>()
    private var isExceptionEnabled = false

    override fun getCities(): Flow<List<City>> {
        if (isExceptionEnabled) {
            throw Exception("Test Exception")
        }
        cityFlow.tryEmit(cityList)
        return cityFlow
    }

    override suspend fun updateCity(city: City) {
        if (isExceptionEnabled) {
            throw Exception("Test Exception")
        }
        val _city = cityList.find { it.id == city.id }
        if (_city != null) {
            val index = cityList.indexOf(_city)
            cityList[index] = city
        } else {
            cityList.add(city)
        }
        cityFlow.tryEmit(cityList)
    }

    override suspend fun getCity(cityId: Int): City {
        if (isExceptionEnabled) {
            throw Exception("Test Exception")
        }
        return cityList.find { it.id == cityId }!!
    }

    override fun getFavoriteCities(): Flow<List<City>> {
        if (isExceptionEnabled) {
            throw Exception("Test Exception")
        }
        val favorites = cityList.filter { city -> city.isFavorite }
        cityFlow.tryEmit(favorites)
        return cityFlow
    }

    /**
     * Test only method to add the cities to the stored list in memory
     */
    fun sendCities(cities: List<City>) {
        cityList = mutableListOf()
        cityList.addAll(cities)
        cityFlow.tryEmit(cities)
    }

    fun sendThrowException(isExceptionEnabled: Boolean) {
        this.isExceptionEnabled = isExceptionEnabled
    }
}