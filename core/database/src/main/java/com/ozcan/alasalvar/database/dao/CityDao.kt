package com.ozcan.alasalvar.database.dao

import androidx.room.*
import com.ozcan.alasalvar.database.model.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("Select * from cities where isCurrentLocation = 0")
    fun getCities(): Flow<List<CityEntity>>

    @Query("Select * from cities where isCurrentLocation = 0 and isFavorite = 1")
    fun getFavorites(): Flow<List<CityEntity>>

    @Query("Select * from cities where isCurrentLocation = 1")
    suspend fun getCurrentLocation(): CityEntity

    @Query("Select * from cities")
    suspend fun getCityList(): List<CityEntity>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCities(cityList: List<CityEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStation(city: CityEntity)

    @Delete
    suspend fun delete(city: CityEntity)

    @Query("SELECT * FROM cities WHERE id IS :cityId")
    suspend fun getCity(cityId: Int): CityEntity
}