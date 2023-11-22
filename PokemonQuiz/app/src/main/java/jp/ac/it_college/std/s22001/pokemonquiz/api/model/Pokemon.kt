package jp.ac.it_college.std.s22001.pokemonquiz.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val sprites: PokemonSprites
)

