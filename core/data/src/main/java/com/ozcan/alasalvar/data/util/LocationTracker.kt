package com.ozcan.alasalvar.data.util

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}