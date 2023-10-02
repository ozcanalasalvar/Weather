package com.ozcan.alasalvar.data.mapper


import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

fun String.asImageUrl() = "https://openweathermap.org/img/wn/$this@2x.png"

fun Double.asTemperature() = "" + this.roundToInt() + "°"


fun Int.asCurrentDate(): String {

    val time = Date(this.toLong() * 1000)

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time.time

    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return "" + dayName(calendar.timeInMillis) + ", " + day + " " + monthName(calendar.timeInMillis)

}

fun Int.asDailyDate(): String {

    val time = Date(this.toLong() * 1000)

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time.time

    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return "" + dayName(prefix = "EEE", timeStamp = calendar.timeInMillis) + ", " + day

}

fun Int.asHour(): String {
    val time = Date(this.toLong() * 1000)

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = time.time

    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    return toFormattedTime(
        hour,
        minute
    )
}


internal fun dayName(timeStamp: Long, prefix: String = "EEEE"): String? {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeStamp
    val date = calendar.timeInMillis
    return SimpleDateFormat(prefix, Locale.ENGLISH).format(date)
}

internal fun monthName(timeStamp: Long): String? {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeStamp
    val date = calendar.timeInMillis
    return SimpleDateFormat("MMM", Locale.ENGLISH).format(date)
}

internal fun toFormattedTime(hour: Int, minute: Int): String {
    var welFormat = ""
    welFormat += if (hour < 10) "0$hour" else "" + hour
    welFormat += ":"
    welFormat += if (minute < 10) "0$minute" else "" + minute
    return welFormat
}