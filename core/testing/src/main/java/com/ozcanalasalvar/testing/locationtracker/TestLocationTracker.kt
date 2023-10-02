package com.ozcanalasalvar.testing.locationtracker

import com.ozcan.alasalvar.data.util.Location
import com.ozcan.alasalvar.data.util.LocationTracker
import com.ozcan.alasalvar.model.data.City
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestLocationTracker : LocationTracker {

    private val locationFlow: MutableSharedFlow<Location?> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)


    override fun getCurrentLocation(): Flow<com.ozcan.alasalvar.data.util.Location?> {
        return locationFlow
    }

    fun sendLocation(city: City?) {
        if (city == null) locationFlow.tryEmit(null)
        else locationFlow.tryEmit(Location(city.lat, city.lon))
    }
}