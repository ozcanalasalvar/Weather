package com.ozcan.alasalvar.network.di

import com.ozcan.alasalvar.network.WeatherDataSource
import com.ozcan.alasalvar.network.service.WeatherNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }
}


@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindRepository(dataSource: WeatherNetwork): WeatherDataSource

}