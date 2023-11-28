package jp.ac.it_college.std.s22001.pokemonquiz.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSprites(
    @SerialName("front_default") val frontDefault: String,
    val other: PokemonSpritesOther
)

@Serializable
data class PokemonSpritesOther(
    @SerialName("official-artwork") val officialArtwork: SpriteResources
)

@Serializable
data class SpriteResources(
    @SerialName("front_default") val frontDefault: String,
    @SerialName("front_shiny") val frontShiny: String?,
)