package com.ozcan.alasalvar.data.repository

import com.ozcan.alasalvar.common.dispatcher.AppDispatchers
import com.ozcan.alasalvar.common.dispatcher.Dispatcher
import com.ozcan.alasalvar.database.CityDataSource
import com.ozcan.alasalvar.model.data.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val cityDataSource: CityDataSource
) : CityRepository {

    override fun getCities(): Flow<List<City>> {
        return cityDataSource.getCities()
    }

    override suspend fun updateStation(city: City) {
        withContext(ioDispatcher) {
            if (city.isCurrentLocation)
                cityDataSource.delete(city)
            cityDataSource.updateStation(city)
        }
    }

    override suspend fun getCity(cityId: Int): City = cityDataSource.getCity(cityId)

    override fun getFavoriteCities(): Flow<List<City>> {
        return cityDataSource.getFavoriteCities()
    }

    suspend fun getCurrentLocation(): City {
        return cityDataSource.getCurrentLocation()
    }

}