package cl.uchile.postgrado.mobile.sensorexample.ui

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cl.uchile.postgrado.mobile.sensorexample.model.GyroscopeSensorUIState
import cl.uchile.postgrado.mobile.sensorexample.model.LuxSensorUIState
import cl.uchile.postgrado.mobile.sensorexample.model.MagneticSensorUIState
import cl.uchile.postgrado.mobile.sensorexample.model.MotionSensorUIState
import cl.uchile.postgrado.mobile.sensorexample.model.ProximitySensorUIState
import cl.uchile.postgrado.mobile.sensorexample.model.StepSensorUIState
import cl.uchile.postgrado.mobile.sensorexample.viewmodel.MotionSensorViewModel

@Composable
fun SensorView() {
    val motionSensorViewModel =
        MotionSensorViewModel(LocalContext.current.applicationContext as Application)

    val motionSensorUIState: State<MotionSensorUIState> =
        motionSensorViewModel.motionSensor.collectAsState()
    val proximitySensorUIState: State<ProximitySensorUIState> =
        motionSensorViewModel.proximitySensor.collectAsState()
    val magneticSensorUIState: State<MagneticSensorUIState> =
        motionSensorViewModel.magneticSensor.collectAsState()
    val luxSensorUIState: State<LuxSensorUIState> =
        motionSensorViewModel.luxSensor.collectAsState()
    val gyroscopeSensorUIState: State<GyroscopeSensorUIState> =
        motionSensorViewModel.gyroscopeSensor.collectAsState()
    val stepSensorUIState: State<StepSensorUIState> =
        motionSensorViewModel.stepSensor.collectAsState()

    LaunchedEffect(Unit) {
        motionSensorViewModel.starListening()
    }

    DisposableEffect(Unit) {
        onDispose {
            motionSensorViewModel.stopListening()
        }
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            Text(
                text = "Accelerometer Sensor",
                modifier = Modifier.padding(start = 16.dp)
            )
            Row(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "X: ${String.format("%.1f", motionSensorUIState.value.x)} m/s2",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Y: ${String.format("%.1f", motionSensorUIState.value.y)} m/s2",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Z: ${String.format("%.1f", motionSensorUIState.value.z)} m/s2",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            Text(
                text = "Proximity Sensor",
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "d: ${String.format("%.1f", proximitySensorUIState.value.distance)} cm",
                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            Text(
                text = "Magnetic Sensor",
                modifier = Modifier.padding(start = 16.dp)
            )
            Row(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "X: ${String.format("%.1f", magneticSensorUIState.value.x)} uT",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Y: ${String.format("%.1f", magneticSensorUIState.value.y)} uT",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Z: ${String.format("%.1f", magneticSensorUIState.value.z)} uT",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            Text(
                text = "Light Sensor",
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "lux: ${String.format("%.1f", luxSensorUIState.value.lux)} lx",
                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            Text(
                text = "Gyroscope Sensor",
                modifier = Modifier.padding(start = 16.dp)
            )
            Row(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "X: ${String.format("%.1f", gyroscopeSensorUIState.value.x)} rad/s",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Y: ${String.format("%.1f", gyroscopeSensorUIState.value.y)} rad/s",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Z: ${String.format("%.1f", gyroscopeSensorUIState.value.z)} rad/s",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            Text(
                text = "Step Counter",
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "Steps: ${stepSensorUIState.value.steps}",
                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        }
    }
}