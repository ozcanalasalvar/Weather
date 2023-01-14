package com.ozcan.alasalvar.data.di

import com.ozcan.alasalvar.data.CityRepository
import com.ozcan.alasalvar.data.core.CityRepositoryImpl
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
}