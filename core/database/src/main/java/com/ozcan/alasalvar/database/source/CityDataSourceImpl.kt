package com.ozcan.alasalvar.database.source

import com.ozcan.alasalvar.common.dispatcher.AppDispatchers.IO
import com.ozcan.alasalvar.common.dispatcher.Dispatcher
import com.ozcan.alasalvar.database.CityDataSource
import com.ozcan.alasalvar.database.CityDatabase
import com.ozcan.alasalvar.database.fake.AssetManager
import com.ozcan.alasalvar.database.model.asEntity
import com.ozcan.alasalvar.database.model.toEntityList
import com.ozcan.alasalvar.database.model.asExternalModel
import com.ozcan.alasalvar.model.data.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class CityDataSourceImpl @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    cityDatabase: CityDatabase,
    private val assets: AssetManager,
    private val networkJson: Json,
) : CityDataSource {

    private val dao = cityDatabase.cityDao()

    companion object {
        private const val CITY_ASSET = "city.json"
    }


    @OptIn(ExperimentalSerializationApi::class)
    override fun getCities(): Flow<List<City>> {
        CoroutineScope(ioDispatcher).launch {
            val cache = dao.getCityList()
            if (cache.isNullOrEmpty()) {
                val cities: List<City> = assets.open(CITY_ASSET).use(networkJson::decodeFromStream)
                dao.insertCities(cities.toEntityList())
            }
        }

        return dao.getCities().map { city -> city.map { it.asExternalModel() } }
    }


    override suspend fun updateStation(city: City) {
        dao.updateStation(city.asEntity())
    }
}