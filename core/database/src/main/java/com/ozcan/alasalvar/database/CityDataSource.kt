package com.ozcan.alasalvar.database

import com.ozcan.alasalvar.model.data.City
import kotlinx.coroutines.flow.Flow

interface CityDataSource {

    fun getCities(): Flow<List<City>>

    suspend fun updateStation(city: City)
}