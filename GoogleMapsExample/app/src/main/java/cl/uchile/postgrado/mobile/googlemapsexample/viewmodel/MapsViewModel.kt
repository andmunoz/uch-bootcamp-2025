package cl.uchile.postgrado.mobile.googlemapsexample.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import cl.uchile.postgrado.mobile.googlemapsexample.model.GeoLocale
import cl.uchile.postgrado.mobile.googlemapsexample.model.GeocodingRepository
import cl.uchile.postgrado.mobile.googlemapsexample.model.LocationRepository
import cl.uchile.postgrado.mobile.googlemapsexample.model.MapUIState
import cl.uchile.postgrado.mobile.googlemapsexample.model.Rotation
import cl.uchile.postgrado.mobile.googlemapsexample.model.UserUIState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapsViewModel(private val locationRepository: LocationRepository, private val geocodingRepository: GeocodingRepository): ViewModel() {
    /* private var _cameraPosition = MutableStateFlow<CameraPositionState?>(null)
    val cameraPosition: StateFlow<CameraPositionState?> = _cameraPosition */

    private var _mapState: MutableStateFlow<MapUIState> = MutableStateFlow(MapUIState(
        infoMarker = null
    ))
    val mapState: StateFlow<MapUIState> = _mapState

    init {
        locationRepository.registerSensorListener()
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun loadUserLocation(context: Context) {
        locationRepository.getLastKnownLocation(
            onSuccess = { location ->
                location?.let {
                    setCameraPosition(LatLng(it.latitude, it.longitude))
                }
            },
            onError = { exception ->
                Log.println(Log.ERROR, "MapsViewModel", "Error: $exception")
                exception.printStackTrace()
            }
        )
    }

    fun getActualUserLocation(): StateFlow<UserUIState> {
        return locationRepository.userLocation
    }

    fun getCoordinatesFromAddress(address: String) {
        val latLng = geocodingRepository.getCoordinatesFromAddress(address)
        if (latLng != null) {
            _mapState.value?.cameraPosition = CameraPositionState(
                position = CameraPosition.fromLatLngZoom(latLng, 15f)
            )
        }
    }

    fun getAddressFromCoordinates(latLng: LatLng) : String {
        val address = geocodingRepository.getAddressFromCoordinates(latLng.latitude, latLng.longitude)
        return address?: ""
    }

    fun setCameraPosition(latLng: LatLng) {
        val userLocation = getActualUserLocation().value
        Log.d("MapsViewModel", "Rotation: ${userLocation.rotation.orientation}")
        _mapState.value?.cameraPosition = CameraPositionState(
            CameraPosition.builder()
                .target(latLng)
                .zoom(15f)
                .bearing(userLocation.rotation.orientation)
                .tilt(45f)
                .build()
        )
    }

    fun getCameraPosition(): CameraPositionState? {
        return _mapState.value.cameraPosition
    }
}