package com.ozcan.alasalvar.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozcan.alasalvar.database.dao.CityDao
import com.ozcan.alasalvar.database.model.CityEntity

@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}