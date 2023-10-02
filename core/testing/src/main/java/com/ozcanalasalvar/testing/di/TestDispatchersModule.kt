package com.ozcanalasalvar.testing.di

import com.ozcan.alasalvar.common.dispatcher.AppDispatchers
import com.ozcan.alasalvar.common.dispatcher.Dispatcher
import com.ozcan.alasalvar.common.dispatcher.di.DispatchersModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class],
)
object TestDispatchersModule {
    @Provides
    @Dispatcher(AppDispatchers.IO)
    fun providesIODispatcher(testDispatcher: TestDispatcher): CoroutineDispatcher = testDispatcher

    @Provides
    @Dispatcher(AppDispatchers.Default)
    fun providesDefaultDispatcher(
        testDispatcher: TestDispatcher,
    ): CoroutineDispatcher = testDispatcher
}