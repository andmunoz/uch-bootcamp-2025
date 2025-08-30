package cl.uchile.postgrado.mobile.indicadores.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.postgrado.mobile.indicadores.service.IndicadoresApiService
import cl.uchile.postgrado.mobile.indicadores.service.IndicadoresRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IndexViewModel : ViewModel() {
    var indexType by mutableStateOf("")
    var index by mutableStateOf("")
    var date by mutableStateOf("")

    fun onIndexChange(newIndex: String) {
        index = newIndex
    }

    fun onDateChange(newDate: String) {
        date = newDate
    }

    /* fun getIndexOptions(): Any {
        return when (indexType) {
            "Nacionales" -> IndicadorNacionalEnumeration.entries.toTypedArray()
            "Internacionales" -> IndicadorInternacionalEnumeration.entries.toTypedArray()
            else -> emptyList<Any>()
        }
    } */

    private var _businessIndex = MutableStateFlow<Indicador>(Indicador(
        codigo = "",
        nombre = "",
        unidad_medida = "",
        serie = emptyList()
    ))
    val businessIndex: StateFlow<Indicador> = _businessIndex
    fun getIndex() {
        viewModelScope.launch {
            try {
                val result = IndicadoresApiService
                    .ApiInstance
                    .api
                    .obtenerIndicadorPorFecha(index, date)
                _businessIndex.value = result
            }
            catch (e: Exception) {
                val errorMessage = e.message ?: "Error desconocido"
            }
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
            val dateRegex = Regex("\\d{2}-\\d{2}-\\d{4}")
            if (!date.matches(dateRegex)) {
                dateErrorMessage = "Por favor, ingrese una fecha válida"
                return Result.failure(Exception(dateErrorMessage))
            }
        }
        return Result.success(Unit)
    }
}