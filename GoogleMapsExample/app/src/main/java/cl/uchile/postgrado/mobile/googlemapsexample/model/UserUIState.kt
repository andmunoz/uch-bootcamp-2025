package cl.uchile.postgrado.mobile.googlemapsexample.model

data class GeoLocale(
    val lat: Double,
    val lng: Double
)

data class Rotation(
    val orientation: Float = 0f
)

data class UserUIState(
    var position: GeoLocale,
    var rotation: Rotation
)
