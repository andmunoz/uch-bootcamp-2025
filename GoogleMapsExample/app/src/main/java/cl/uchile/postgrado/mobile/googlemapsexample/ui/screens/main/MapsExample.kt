package cl.uchile.postgrado.mobile.googlemapsexample.ui.screens.main

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import cl.uchile.postgrado.mobile.googlemapsexample.R
import cl.uchile.postgrado.mobile.googlemapsexample.model.GeocodingRepository
import cl.uchile.postgrado.mobile.googlemapsexample.model.InfoMarker
import cl.uchile.postgrado.mobile.googlemapsexample.model.LocationRepository
import cl.uchile.postgrado.mobile.googlemapsexample.viewmodel.MapsViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.model.cameraPosition

@Composable
fun MapsExample(
    mapsViewModel: MapsViewModel = MapsViewModel(
        LocationRepository(LocalContext.current),
        GeocodingRepository(LocalContext.current)
    )
) {
    val context = LocalContext.current

    val mapState by mapsViewModel.mapState.collectAsState()
    val userLocation = mapsViewModel.getActualUserLocation().collectAsState()
    var address by remember { mutableStateOf("") }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            mapsViewModel.loadUserLocation(context)
        } else {
            Toast.makeText(context, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mapsViewModel.loadUserLocation(context)
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
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
                    label = { Text(stringResource(R.string.address)) },
                )
                Button(
                    onClick = {
                        mapsViewModel.getCoordinatesFromAddress(address)
                    }
                ) {
                    Text(stringResource(R.string.search))
                }
            }

            Log.d("MapsExample", "MapState: $mapState")
            val cameraPosition = mapsViewModel.getCameraPosition()

            if (cameraPosition != null) {
                Log.d("MapsExample", "CameraPosition: ${cameraPosition.position}")
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPosition,
                    properties = MapProperties(
                        mapType = mapState.mapType
                    ),
                    onMapClick = { latLng ->
                        mapState.infoMarker = null
                        mapsViewModel.setCameraPosition(latLng)
                    },
                    onMapLongClick = { latLng ->
                        val address = mapsViewModel.getAddressFromCoordinates(latLng)
                        mapState.infoMarker = InfoMarker(
                            position = MarkerState(position = latLng),
                            title = address,
                            snippet = "Lat: ${latLng.latitude}, Long: ${latLng.longitude}",
                            visible = true
                        )
                    }
                ) {
                    if (mapState.infoMarker != null) {
                        val marker = mapState?.infoMarker!!
                        Log.d("MapsExample", "Marker: ${marker}")
                        Marker(
                            state = marker.position,
                            title = marker.title,
                            snippet = marker.snippet,
                            visible = marker.visible
                        )
                    }
                }
            } else {
                Text(
                    text = stringResource(R.string.loading_map),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                )
            }
        }
    }
}

