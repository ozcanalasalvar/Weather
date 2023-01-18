package com.ozcan.alasalvar.data

import com.ozcan.alasalvar.model.data.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    fun getCities(): Flow<List<City>>

    suspend fun updateStation(city: City)

    suspend fun getCity(cityId: Int): City

}