package cl.uchile.postgrado.mobile.pokedex.model.data

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val name: String,
    val url: String
)

data class PokemonResults(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val species: PokemonSpecies,
    val sprites: PokemonSprites
)

data class PokemonSpecies(
    val name: String,
    val url: String
)

data class PokemonSprites(
    @SerializedName("front_default") val frontDefault: String,
    @SerializedName("back_default") val backDefault: String,
    val other: PokemonOtherSprites
)

data class PokemonOtherSprites(
    @SerializedName("dream_world") val dreamWorld: PokemonDreamWorldSprites
)

data class PokemonDreamWorldSprites(
    @SerializedName("front_default") val image: String
)