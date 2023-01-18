package com.ozcan.alasalvar.data.di

import com.ozcan.alasalvar.data.CityRepository
import com.ozcan.alasalvar.data.WeatherRepository
import com.ozcan.alasalvar.data.source.CityRepositoryImpl
import com.ozcan.alasalvar.data.source.WeatherRepositoryImpl
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
    fun bindRepository(repository: CityRepositoryImpl): CityRepository

    @Binds
    @Singleton
    fun bindRepository(repository: WeatherRepositoryImpl): WeatherRepository
}