package com.ozcan.alasalvar.data.util

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationTracker {
     fun getCurrentLocation(): Flow<Location?>
}