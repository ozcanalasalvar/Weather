package com.ozcan.alasalvar.database.dao

import androidx.room.*
import com.ozcan.alasalvar.database.model.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("Select * from cities")
    fun getCities(): Flow<List<CityEntity>>

    @Query("Select * from cities")
    suspend fun getCityList(): List<CityEntity>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCities(cityList: List<CityEntity>)

    @Update
    suspend fun updateStation(city: CityEntity)
}