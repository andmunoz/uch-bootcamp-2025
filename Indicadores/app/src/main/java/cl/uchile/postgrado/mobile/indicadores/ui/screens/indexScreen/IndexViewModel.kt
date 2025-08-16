package cl.uchile.postgrado.mobile.indicadores.ui.screens.indexScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class IndexViewModel : ViewModel() {
    val indexTypeOptions = listOf("Nacionales", "Internacionales")
    val indexNationalOptions = listOf("UF", "UTM", "UTA")
    val indexInternationalOptions = listOf("Dólar", "Euro")

    var indexType by mutableStateOf("")
    var index by mutableStateOf("")
    var date by mutableStateOf("")

    /* fun onIndexTypeChange(newIndex: String) {
        indexType = newIndex
    } */

    fun onIndexChange(newIndex: String) {
        index = newIndex
    }

    fun onDateChange(newDate: String) {
        date = newDate
    }

    fun getIndexOptions(): List<String> {
        return when (indexType) {
            "Nacionales" -> indexNationalOptions
            "Internacionales" -> indexInternationalOptions
            else -> emptyList()
        }
    }

    var indexErrorMessage: String? by mutableStateOf(null)
    var dateErrorMessage: String? by mutableStateOf(null)

    fun validateForm(): Result<Unit> {
        indexErrorMessage = null
        dateErrorMessage = null

        if (indexType.isEmpty() || index.isEmpty()) {
            indexErrorMessage = "Por favor, seleccione el indicador"
            return Result.failure(Exception(indexErrorMessage))
        }
        if (date.isEmpty()) {
            dateErrorMessage = "Por favor, ingrese una fecha"
            return Result.failure(Exception(dateErrorMessage))
        } else {
            val dateRegex = Regex("\\d{2}/\\d{2}/\\d{4}")
            if (!date.matches(dateRegex)) {
                dateErrorMessage = "Por favor, ingrese una fecha válida"
                return Result.failure(Exception(dateErrorMessage))
            }
        }
        return Result.success(Unit)
    }
}