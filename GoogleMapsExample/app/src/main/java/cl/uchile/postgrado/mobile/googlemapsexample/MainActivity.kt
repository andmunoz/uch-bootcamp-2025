package cl.uchile.postgrado.mobile.googlemapsexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.uchile.postgrado.mobile.googlemapsexample.ui.theme.GoogleMapsExampleTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoogleMapsExampleTheme {
                MapsExample()
            }
        }
    }
}

@Composable
fun MapsExample() {
    val santiago = LatLng(-33.4489, -70.6693)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(santiago, 10f)
    }
    val markerState = rememberMarkerState(position = santiago)
    val uiSettings by remember { mutableStateOf(
        MapUiSettings(
            zoomControlsEnabled = true,
            compassEnabled = true,
            mapToolbarEnabled = true,
            myLocationButtonEnabled = true
        )
    )}

    Scaffold { innerPadding ->
        GoogleMap(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                mapType = MapType.NORMAL
            ),
            onMapClick = { latLng ->
                markerState.position = latLng
            },
            onMapLongClick = { latLng ->
                println("Lat: ${latLng.latitude}, Lng: ${latLng.longitude}")
            }
        ) {
            Marker(
                state = markerState,
                title = "Santiago",
                snippet = "Capital de Chile",
                draggable = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapsExamplePreview() {
    GoogleMapsExampleTheme {
        MapsExample()
    }
}