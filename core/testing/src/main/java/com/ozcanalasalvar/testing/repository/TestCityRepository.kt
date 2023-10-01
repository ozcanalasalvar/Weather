package com.ozcanalasalvar.testing.repository

import com.ozcan.alasalvar.data.CityRepository
import com.ozcan.alasalvar.model.data.City
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TestCityRepository : CityRepository {


    private val cityFlow: MutableSharedFlow<List<City>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getCities(): Flow<List<City>> {
        return cityFlow
    }

    override suspend fun updateStation(city: City) {
        cityFlow.map { cities -> cities.filter { it.id == city.id }.map { city } }
    }

    override suspend fun getCity(cityId: Int): City {
        return cityFlow.map { cities -> cities.find { it.id == cityId }!! }.first()
    }

    override fun getFavoriteCities(): Flow<List<City>> {
        return cityFlow.map { cities -> cities.filter { it.isFavorite } }
    }

    /**
     * Test only method to add the cities to the stored list in memory
     */
    fun sendCities(cities: List<City>) {
        cityFlow.tryEmit(cities)
    }
}