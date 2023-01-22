package com.ozcan.alasalvar.database.di

import android.content.Context
import androidx.room.Room
import com.ozcan.alasalvar.database.CityDataSource
import com.ozcan.alasalvar.database.CityDatabase
import com.ozcan.alasalvar.database.dao.CityDao
import com.ozcan.alasalvar.database.fake.AssetManager
import com.ozcan.alasalvar.database.source.CityDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesAssetManager(
        @ApplicationContext context: Context,
    ): AssetManager = AssetManager(context.assets::open)


    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): CityDatabase = Room.databaseBuilder(
        context,
        CityDatabase::class.java,
        "city-database"
    ).build()
}


@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindRepository(dataSource: CityDataSourceImpl): CityDataSource

}