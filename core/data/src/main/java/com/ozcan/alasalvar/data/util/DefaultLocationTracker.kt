package com.ozcan.alasalvar.data.util

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject



class DefaultLocationTracker @Inject constructor(
    private val application: Application
) : LocationTracker {

    override  fun getCurrentLocation() = callbackFlow {
        val locationClient = LocationServices.getFusedLocationProviderClient(application)
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled) {
            trySend(null).isSuccess
        }
        val mLocationRequest = LocationRequest.create().apply {
            interval = 10
            fastestInterval = 10
        }


        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result ?: return
                try {
                    trySend(result.lastLocation).isSuccess
                } catch (e: Exception) {
                }
            }
        }
        locationClient.requestLocationUpdates(mLocationRequest, callback, Looper.getMainLooper())
            .addOnFailureListener { e ->
                //close(e) // in case of exception, close the Flow
            }
        // clean up when Flow collection ends
        awaitClose {
            locationClient.removeLocationUpdates(callback)
        }
    }
}