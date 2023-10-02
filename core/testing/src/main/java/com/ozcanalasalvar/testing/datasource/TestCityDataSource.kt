package com.ozcanalasalvar.testing.datasource

import com.ozcan.alasalvar.database.CityDataSource
import com.ozcan.alasalvar.model.data.City
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TestCityDataSource : CityDataSource {

    private val cityFlow: MutableSharedFlow<List<City>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private lateinit var currentCity: City

    override fun getCities(): Flow<List<City>> {
        return cityFlow
    }

    override suspend fun updateCity(_city: City) {

        val list = cityFlow.first().toList().toMutableList()
        val city = list.filter { it.id == _city.id }[0]
        val index = list.indexOf(city)
        list[index] = _city

        cityFlow.tryEmit(list)
    }

    override suspend fun getCity(cityId: Int): City {
        return cityFlow.map { cities -> cities.find { it.id == cityId }!! }.first()
    }

    override fun getFavoriteCities(): Flow<List<City>> {
        return cityFlow.map { cities -> cities.filter { it.isFavorite } }
    }

    override suspend fun delete(city: City) {
        val list = cityFlow.first().toList().filter { it.id != city.id }
        cityFlow.tryEmit(list)
    }

    override suspend fun getCurrentLocation(): City {
        return currentCity
    }

    /**
     * Test only method to add the cities to the stored list in memory
     */
    fun sendCities(cities: List<City>) {
        cityFlow.tryEmit(cities)
    }


    /**
     * Test only method to add the cities to the stored list in memory
     */
    fun sendCurrentCity(city: City) {
        currentCity = city
    }
}