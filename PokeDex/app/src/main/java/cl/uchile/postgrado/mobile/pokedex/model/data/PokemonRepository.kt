package cl.uchile.postgrado.mobile.pokedex.model.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepository {
    private val pokemonApi = PokemonApi.ApiInstance.api
    private val limit = 20
    private val offset = 0

    suspend fun getPokemon(): Flow<List<Pokemon>> = flow {
        val response = pokemonApi.getPokemon(limit, offset)
        emit(response.results)
    }

    suspend fun getPokemon(page: Int): Flow<List<Pokemon>> = flow {
        val offset = page * limit
        val response = pokemonApi.getPokemon(limit, offset)
        emit(response.results)
    }

    suspend fun getPokemonById(id: Int): Flow<PokemonDetail> = flow {
        val response = pokemonApi.getPokemonById(id)
        emit(response)
    }

    suspend fun getPokemonByName(name: String): Flow<PokemonDetail> = flow {
        val response = pokemonApi.getPokemonByName(name)
        emit(response)
    }

    suspend fun getPokemonBySpecie(id: Int): Flow<List<Pokemon>> = flow {
        val response = pokemonApi.getPokemonBySpecie(id).results
        emit(response)
    }
}