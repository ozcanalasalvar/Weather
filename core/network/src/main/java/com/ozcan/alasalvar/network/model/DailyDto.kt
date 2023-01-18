package com.ozcan.alasalvar.network.model

import com.ozcan.alasalvar.model.data.Daily
import com.ozcan.alasalvar.network.util.asDailyDate
import com.ozcan.alasalvar.network.util.asImageUrl
import com.ozcan.alasalvar.network.util.asTemperature

data class DailyDto(
    val dt: Int,
    val temp: TempDto,
    val weather: List<WeatherInfoDto>,
)


fun DailyDto.asExternalModel(): Daily = Daily(
    temperatureMin = temp.min.asTemperature(),
    temperatureMax = temp.max.asTemperature(),
    icon = weather[0].icon.asImageUrl(),
    weatherStatus = weather[0].main,
    date = dt.asDailyDate(),
)
