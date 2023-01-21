package com.ozcan.alasalvar.database.model

import com.ozcan.alasalvar.model.data.City


fun List<City>.toEntityList(): List<CityEntity> {
    return this.map {
        it.asEntity()
    }
}

fun City.asEntity(): CityEntity = CityEntity(
    id = id,
    country = country,
    lat = lat,
    lon = lon,
    name = name,
    isFavorite = isFavorite,
    isCurrentLocation = isCurrentLocation,
)