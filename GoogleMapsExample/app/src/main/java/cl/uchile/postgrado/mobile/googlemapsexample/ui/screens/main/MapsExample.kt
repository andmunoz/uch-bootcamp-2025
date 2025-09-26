package cl.uchile.postgrado.mobile.googlemapsexample.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import cl.uchile.postgrado.mobile.googlemapsexample.model.GeocodingRepository
import cl.uchile.postgrado.mobile.googlemapsexample.model.LocationRepository
import cl.uchile.postgrado.mobile.googlemapsexample.viewmodel.MapsViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun MapsExample(
    mapsViewModel: MapsViewModel = MapsViewModel(
        LocationRepository(LocalContext.current),
        GeocodingRepository(LocalContext.current)
    )
) {
    val cameraPosition by mapsViewModel.cameraPosition.collectAsState()
    var address by remember { mutableStateOf("") }
    var latLng by remember { mutableStateOf(LatLng(0.0, 0.0)) }
    var markerState by remember { mutableStateOf<MarkerState?>(MarkerState(position = latLng)) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        mapsViewModel.loadUserLocation(context)
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
            ) {
                TextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Dirección") },
                )
                Button(
                    onClick = {
                        mapsViewModel.getCoordinatesFromAddress(address)
                    }
                ) {
                    Text("Buscar")
                }
            }
            cameraPosition?.let { camPos ->
                latLng = camPos.position.target
                markerState = MarkerState(position = latLng)
                GoogleMap(
                    modifier = Modifier
                        .fillMaxSize(),
                    cameraPositionState = camPos,
                    properties = MapProperties(
                        mapType = MapType.NORMAL
                    )
                ) {
                    markerState?.let { marker ->
                        Marker(
                            state = marker,
                            title = mapsViewModel.getAddressFromCoordinates(latLng),
                            snippet = "Lat: ${latLng.latitude}, Lng: ${latLng.longitude}",
                            draggable = false
                        )
                    }
                }
            } ?: run {
                Text(
                    text = "Cargando ubicación...",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                )
            }
        }
    }
}

