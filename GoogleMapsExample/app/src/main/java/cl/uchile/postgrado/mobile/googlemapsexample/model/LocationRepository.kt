package cl.uchile.postgrado.mobile.googlemapsexample.model

import android.Manifest
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationServices

class LocationRepository(private val context: Context) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun getLastKnownLocation(
        onSuccess: (Location?) -> Unit,
        onError: (Exception) -> Unit
    ) {
        Log.println(Log.INFO, "LocationRepository", "Getting Last Known Location")
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                Log.println(Log.INFO, "LocationRepository", "Location: $location")
                onSuccess(location)
            }
            .addOnFailureListener { error ->
                Log.println(Log.ERROR, "LocationRepository", "Error: $error")
                onError(error)
            }
    }
}