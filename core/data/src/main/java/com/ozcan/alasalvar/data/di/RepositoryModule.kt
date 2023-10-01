package com.ozcan.alasalvar.data.di

import com.ozcan.alasalvar.data.repository.CityRepository
import com.ozcan.alasalvar.data.repository.WeatherRepository
import com.ozcan.alasalvar.data.repository.CityRepositoryImpl
import com.ozcan.alasalvar.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCityRepository(cityRepository: CityRepositoryImpl): CityRepository

    @Binds
    @Singleton
    fun bindWeatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository
}