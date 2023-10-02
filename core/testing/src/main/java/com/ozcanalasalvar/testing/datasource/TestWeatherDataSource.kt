package com.ozcanalasalvar.testing.datasource

import com.ozcan.alasalvar.network.WeatherDataSource
import com.ozcan.alasalvar.network.model.CoordinateDto
import com.ozcan.alasalvar.network.model.DailyDto
import com.ozcan.alasalvar.network.model.HourlyDto
import com.ozcan.alasalvar.network.model.MainDto
import com.ozcan.alasalvar.network.model.SysDto
import com.ozcan.alasalvar.network.model.TempDto
import com.ozcan.alasalvar.network.model.WeatherDetailDto
import com.ozcan.alasalvar.network.model.WeatherDto
import com.ozcan.alasalvar.network.model.WeatherInfoDto

class TestWeatherDataSource : WeatherDataSource {

    override suspend fun getWeatherData(cityName: String?, lat: Double?, lon: Double?): WeatherDto {
        return WeatherDto(
            id = 1,
            name = "Istanbul",
            sys = SysDto(country = "Turkey"),
            coord = CoordinateDto(
                lat = 123.4, lon = 123.4
            ),
            dt = 1,
            main = MainDto(
                feels_like = 123.4,
                humidity = 20,
                pressure = 3,
                temp = 27.0,
                temp_max = 35.0,
                temp_min = 15.9
            ),
            timezone = 3,
            weather = listOf(
                WeatherInfoDto(
                    id = 1,
                    main = "Cloudy",
                    icon = "3n",
                    description = "description",
                ),
            ),
        )
    }

    override suspend fun getWeatherDetail(lat: Double, lon: Double): WeatherDetailDto {
        return WeatherDetailDto(
            current = HourlyDto(
                dt = 3,
                feels_like = 28.0,
                humidity = 23,
                pressure = 23,
                wind_speed = 23.0,
                temp = 28.0,
                weather = listOf(
                    WeatherInfoDto(
                        id = 1,
                        main = "Cloudy",
                        icon = "3n",
                        description = "asd",
                    )
                ),
            ),
            daily = listOf(
                DailyDto(
                    dt = 3,
                    temp = TempDto(
                        day = 123.4,
                        eve = 123.4,
                        max = 123.4,
                        min = 123.4,
                        morn = 123.4,
                        night = 123.4,
                    ),
                    weather = listOf(
                        WeatherInfoDto(
                            id = 1,
                            main = "Cloudy",
                            icon = "3n",
                            description = "asd",
                        )
                    ),
                )
            ),
            hourly = listOf(
                HourlyDto(
                    dt = 3,
                    feels_like = 28.0,
                    humidity = 23,
                    pressure = 23,
                    wind_speed = 23.0,
                    temp = 28.0,
                    weather = listOf(
                        WeatherInfoDto(
                            id = 1,
                            main = "Cloudy",
                            icon = "3n",
                            description = "asd",
                        )
                    ),
                )
            ),
            lat = 123.4,
            lon = 123.4,
            timezone = "2",
            timezone_offset = 3,
        )
    }
}