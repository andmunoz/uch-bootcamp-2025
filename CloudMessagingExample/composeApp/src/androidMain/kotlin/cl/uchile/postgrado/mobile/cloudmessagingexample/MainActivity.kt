package cl.uchile.postgrado.mobile.cloudmessagingexample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import cl.uchile.postgrado.mobile.cloudmessagingexample.model.AndroidNotificationRepository
import cl.uchile.postgrado.mobile.cloudmessagingexample.viewmodel.NotificationViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private const val NOTIFICATION_REQUEST_CODE = 1234
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val viewModel = NotificationViewModel(AndroidNotificationRepository)
        setContent {
            askNotificationPermission()
            App(viewModel)
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // ToDo: Por desarrollar
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private suspend fun getAndStoreRegToken(): String {
        val token = Firebase.messaging.token.await()
        // Add token and timestamp to Firestore for this user
        val deviceToken = hashMapOf(
            "token" to token,
            "timestamp" to FieldValue.serverTimestamp(),
        )

        // Get user ID from Firebase Auth or your own server
        Firebase.firestore.collection("fcmTokens").document("myuserid")
            .set(deviceToken).await()
        return token
    }

    fun logRegToken() {
        // [START log_reg_token]
        Firebase.messaging.getToken().addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = "FCM Registration token: $token"
            Log.d(TAG, msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        }
        // [END log_reg_token]
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    // App(viewModel)
}

