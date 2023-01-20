package com.ozcan.alasalvar.network.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ozcan.alasalvar.network.WeatherDataSource
import com.ozcan.alasalvar.network.model.WeatherDetailDto
import com.ozcan.alasalvar.network.model.WeatherDto
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import weather.core.network.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

private interface WeatherService {

    @GET("weather")
    suspend fun getWeatherData(
        @Query("q") query: String? = null,
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null,
    ): WeatherDto

    @GET("onecall?units=metric&exclude=minutely")
    suspend fun getWeatherDetail(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
    ): WeatherDetailDto

}

private const val QUERY_APP_ID = "APPID"
private const val BACKEND_URL = "https://api.openweathermap.org/data/2.5/"

@Singleton
class WeatherNetwork @Inject constructor(networkJson: Json) : WeatherDataSource {

    private val client = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            var request: Request = chain.request()
            val url: HttpUrl =
                request.url.newBuilder()
                    .addQueryParameter(QUERY_APP_ID, BuildConfig.API_KEY)
                    .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        })
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        })


    private val service = Retrofit.Builder()
        .baseUrl(BACKEND_URL)
        .client(client.build())
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(WeatherService::class.java)


    override suspend fun getWeatherData(cityName: String?, lat: Double?, lon: Double?): WeatherDto =
        service.getWeatherData(cityName, lat, lon)

    override suspend fun getWeatherDetail(lat: Double, lon: Double): WeatherDetailDto =
        service.getWeatherDetail(lat, lon)
}