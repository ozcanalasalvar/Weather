package com.ozcan.alasalvar.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ozcan.alasalvar.model.data.City

@Entity(
    tableName = "cities",
)
data class CityEntity(
    @PrimaryKey
    val id: Int,
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val isFavorite: Boolean = false
)

fun CityEntity.toExternalModel() = City(
    id = id,
    country = country,
    lat = lat,
    lon = lon,
    name = name,
    isFavorite = isFavorite,
)