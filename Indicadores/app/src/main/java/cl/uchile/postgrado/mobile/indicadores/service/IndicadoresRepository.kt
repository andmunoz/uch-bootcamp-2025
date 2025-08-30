package cl.uchile.postgrado.mobile.indicadores.service

import cl.uchile.postgrado.mobile.indicadores.model.Indicador
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IndicadoresRepository(private val apiService: IndicadoresApiService) {
    suspend fun obtenerIndicador(indicador: String, fecha: String): Flow<Indicador> = flow {
        val indicador = apiService.obtenerIndicadorPorFecha(indicador, fecha)
        emit(indicador)
    }
}