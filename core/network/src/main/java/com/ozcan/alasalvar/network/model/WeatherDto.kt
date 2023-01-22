package com.ozcan.alasalvar.network.model

import com.ozcan.alasalvar.model.data.City
import com.ozcan.alasalvar.model.data.Weather
import com.ozcan.alasalvar.network.util.asCurrentDate
import com.ozcan.alasalvar.network.util.asDailyDate
import com.ozcan.alasalvar.network.util.asImageUrl
import com.ozcan.alasalvar.network.util.asTemperature
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    val id: Int,
    val name: String,
    val sys: SysDto,
    val coord: CoordinateDto,
    val dt: Int,
    val main: MainDto,
    val timezone: Int,
    val weather: List<WeatherInfoDto>,
)


@Serializable
data class CoordinateDto(
    val lat: Double,
    val lon: Double
)


@Serializable
data class MainDto(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

fun WeatherDto.asExternalModel(city: City): Weather {
    return Weather(
        city = city,
        weatherIcon = weather[0].icon.asImageUrl(),
        weatherStatus = weather[0].main,
        currentTemperature = main.temp.asTemperature(),
        todayDate =dt.asDailyDate(),
    )
}


fun WeatherDto.asExternalModel(): Weather {
    return Weather(
        city = City(
            id =id,
            name =name,
            country =sys.country,
            lat =coord.lat,
            lon =coord.lon,
            isFavorite =false,
        ),
        weatherIcon = weather[0].icon.asImageUrl(),
        weatherStatus = weather[0].main,
        currentTemperature = main.temp.asTemperature(),
        todayDate = dt.asDailyDate(),
    )
}

