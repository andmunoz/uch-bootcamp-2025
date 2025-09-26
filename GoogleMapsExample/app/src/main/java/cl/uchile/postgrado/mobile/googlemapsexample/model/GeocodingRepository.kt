package cl.uchile.postgrado.mobile.googlemapsexample.model

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

class GeocodingRepository(private val context: Context) {
    private val geocoder = Geocoder(context, Locale.getDefault())

    fun getCoordinatesFromAddress(address: String): LatLng? {
        val results = geocoder.getFromLocationName(address, 1)
        return if (!results.isNullOrEmpty()) {
            LatLng(results[0].latitude, results[0].longitude)
        } else null
    }

    fun getAddressFromCoordinates(lat: Double, lng: Double): String? {
        val results = geocoder.getFromLocation(lat, lng, 1)
        return if (!results.isNullOrEmpty()) {
            results[0].getAddressLine(0)
        } else null
    }
}