package com.ozcan.alasalvar.database

import com.ozcan.alasalvar.model.data.City
import kotlinx.coroutines.flow.Flow

interface CityDataSource {

    fun getCities(): Flow<List<City>>

    fun getFavoriteCities(): Flow<List<City>>

    suspend fun delete(city: City)

    suspend fun getCurrentLocation(): City

    suspend fun updateStation(city: City)

    suspend fun getCity(cityId: Int): City
}