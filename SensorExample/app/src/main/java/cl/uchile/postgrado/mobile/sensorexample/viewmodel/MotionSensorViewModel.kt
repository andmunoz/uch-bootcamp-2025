package cl.uchile.postgrado.mobile.sensorexample.viewmodel

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import cl.uchile.postgrado.mobile.sensorexample.model.GyroscopeSensorUIState
import cl.uchile.postgrado.mobile.sensorexample.model.LuxSensorUIState
import cl.uchile.postgrado.mobile.sensorexample.model.MagneticSensorUIState
import cl.uchile.postgrado.mobile.sensorexample.model.MotionSensorUIState
import cl.uchile.postgrado.mobile.sensorexample.model.ProximitySensorUIState
import cl.uchile.postgrado.mobile.sensorexample.model.StepSensorUIState
import kotlinx.coroutines.flow.MutableStateFlow

class MotionSensorViewModel(application: Application): AndroidViewModel(application), SensorEventListener {
    private val sensorManager = application.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val _motionSensor = MutableStateFlow(MotionSensorUIState())
    val motionSensor = _motionSensor

    private val proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    private val _proximitySensor = MutableStateFlow(ProximitySensorUIState())
    val proximitySensor = _proximitySensor

    private val magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    private val _magneticSensor = MutableStateFlow(MagneticSensorUIState())
    val magneticSensor = _magneticSensor

    private val lux = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    private val _luxSensor = MutableStateFlow(LuxSensorUIState())
    val luxSensor = _luxSensor

    private val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val _gyroscopeSensor = MutableStateFlow(GyroscopeSensorUIState())
    val gyroscopeSensor = _gyroscopeSensor

    private val stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    private val _stepSensor = MutableStateFlow(StepSensorUIState())
    val stepSensor = _stepSensor

    fun starListening() {
        sensorManager.registerListener(
            this,
            accelerometer,
            SensorManager.SENSOR_DELAY_UI
        )
        sensorManager.registerListener(
            this,
            proximity,
            SensorManager.SENSOR_DELAY_UI
        )
        sensorManager.registerListener(
            this,
            magnetic,
            SensorManager.SENSOR_DELAY_UI
        )
        sensorManager.registerListener(
            this,
            lux,
            SensorManager.SENSOR_DELAY_UI
        )
        sensorManager.registerListener(
            this,
            gyroscope,
            SensorManager.SENSOR_DELAY_UI
        )
        sensorManager.registerListener(
            this,
            stepCounter,
            SensorManager.SENSOR_DELAY_UI
        )
    }

    fun stopListening() {
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            _motionSensor.value = MotionSensorUIState(
                x = event.values[0],
                y = event.values[1],
                z = event.values[2]
            )
        }
        if (event?.sensor?.type == Sensor.TYPE_PROXIMITY) {
            _proximitySensor.value = ProximitySensorUIState(
                distance = event.values[0]
            )
        }
        if (event?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD) {
            _magneticSensor.value = MagneticSensorUIState(
                x = event.values[0],
                y = event.values[1],
                z = event.values[2]
            )
        }
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            _luxSensor.value = LuxSensorUIState(
                lux = event.values[0]
            )
        }
        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            _gyroscopeSensor.value = GyroscopeSensorUIState(
                x = event.values[0],
                y = event.values[1],
                z = event.values[2]
            )
        }
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            _stepSensor.value = StepSensorUIState(
                steps = event.values[0].toInt()
            )
        }
    }
}