package cl.uchile.postgrado.mobile.additionalexamples

import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import cl.uchile.postgrado.mobile.additionalexamples.model.BiometricViewModel
import cl.uchile.postgrado.mobile.additionalexamples.ui.screens.ExamplesScreen
import cl.uchile.postgrado.mobile.additionalexamples.ui.theme.AdditionalExamplesTheme
import java.util.concurrent.Executor

class MainActivity : ComponentActivity() {

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var executor: Executor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val biometricViewModel = BiometricViewModel()
        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = androidx.biometric.BiometricPrompt(
            this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    biometricViewModel.setAuthenticated(false)
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    biometricViewModel.setAuthenticated(true)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    biometricViewModel.setAuthenticated(false)
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación biometrica")
            .setSubtitle("Autenticate para continuar")
            .setNegativeButtonText("Cancelar")
            .build()

        setContent {
            AdditionalExamplesTheme {
                ExamplesScreen(biometricViewModel) {
                    biometricPrompt.authenticate(promptInfo)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExamplesScreenPreview() {
    AdditionalExamplesTheme {
        ExamplesScreen(BiometricViewModel())
    }
}