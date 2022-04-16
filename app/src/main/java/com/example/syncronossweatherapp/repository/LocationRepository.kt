package com.example.syncronossweatherapp.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleOnSubscribe
import javax.inject.Inject

class LocationRepository @Inject constructor(val context: Context): ILocationRepository {

    override suspend fun getLocation(): Location? {
        return Single.create(SingleOnSubscribe<Location> { emitter ->
            val fusedLocationClient: FusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(context)

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
                    location?.let {
                        emitter.onSuccess(it)
                    } ?: run {
                        emitter.onError(Throwable("Location not found"))
                    }
                }
            } else {
                emitter.onError(Throwable("Permission does not granted"))
            }
        }).blockingGet()
    }
}