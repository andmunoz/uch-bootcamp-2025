package cl.uchile.postgrado.mobile.googlemapsexample.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import cl.uchile.postgrado.mobile.googlemapsexample.model.GeocodingRepository
import cl.uchile.postgrado.mobile.googlemapsexample.model.LocationRepository
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapsViewModel(private val locationRepository: LocationRepository, private val geocodingRepository: GeocodingRepository): ViewModel() {
    private var _cameraPosition = MutableStateFlow<CameraPositionState?>(null)
    val cameraPosition: StateFlow<CameraPositionState?> = _cameraPosition

    fun loadUserLocation(context: Context) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.println(Log.INFO, "MapsViewModel", "PERMISSION_GRANTED")
            locationRepository.getLastKnownLocation(
                onSuccess = { location ->
                    Log.println(Log.INFO, "MapsViewModel", "Location: $location")
                    location?.let {
                        _cameraPosition.value = CameraPositionState(
                            position = CameraPosition.fromLatLngZoom(
                                LatLng(location.latitude, location.longitude),
                                15f
                            )
                        )
                    }
                },
                onError = { exception ->
                    Log.println(Log.ERROR, "MapsViewModel", "Error: $exception")
                    _cameraPosition.value = null
                    exception.printStackTrace()
                }
            )
        }
        else {
            Log.println(Log.ERROR, "MapsViewModel", "PERMISSION_DENIED")
            _cameraPosition.value = null
            throw Exception("Permiso de ubicación no otorgado")
        }
    }

    fun getCoordinatesFromAddress(address: String) {
        val latLng = geocodingRepository.getCoordinatesFromAddress(address)
        if (latLng != null) {
            _cameraPosition.value = CameraPositionState(
                position = CameraPosition.fromLatLngZoom(latLng, 15f)
            )
        }
    }

    fun getAddressFromCoordinates(latLng: LatLng) : String {
        val address = geocodingRepository.getAddressFromCoordinates(latLng.latitude, latLng.longitude)
        return address?: ""
    }
}