package com.ozcan.alasalvar.data.util

import kotlinx.coroutines.flow.Flow

interface LocationTracker {
     fun getCurrentLocation(): Flow<Location?>
}