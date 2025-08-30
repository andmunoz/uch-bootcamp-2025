package cl.uchile.postgrado.mobile.indicadores.service

import cl.uchile.postgrado.mobile.indicadores.model.Indicador
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface IndicadoresApiService {
    object ApiInstance {
        private const val BASE_URL = "https://mindicador.cl/api/"
        val api: IndicadoresApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IndicadoresApiService::class.java)
        }
    }

    @GET("{indicador}/{fecha}")
    suspend fun obtenerIndicadorPorFecha(
        @Path("indicador") indicador: String,
        @Path("fecha") fecha: String
    ): Indicador

    /*
        @GET("{indicador}")
        suspend fun obtenerIndicador(
            @Path("indicador") indicador: String
        ): Indicador

        @GET("{indicador}/{anno}")
        suspend fun obtenerIndicadorPorAnno(
            @Path("indicador") indicador: String,
            @Path("anno") anno: String
        ): Indicador
    */
}