package com.ozcan.alasalvar.data.source

import com.ozcan.alasalvar.data.CityRepository
import com.ozcan.alasalvar.database.CityDataSource
import com.ozcan.alasalvar.model.data.City
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(private val cityDataSource: CityDataSource) :
    CityRepository {

    override fun getCities(): Flow<List<City>> {
        return cityDataSource.getCities()
    }

    override suspend fun updateStation(city: City) {
        cityDataSource.updateStation(city)
    }

    override suspend fun getCity(cityId: Int): City = cityDataSource.getCity(cityId)
}