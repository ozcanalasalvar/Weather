package com.ozcan.alasalvar.data.repository

import com.ozcan.alasalvar.model.data.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    fun getCities(): Flow<List<City>>

    suspend fun updateCity(city: City)

    suspend fun getCity(cityId: Int): City

    fun getFavoriteCities(): Flow<List<City>>

}