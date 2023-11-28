package jp.ac.it_college.std.s22001.pokemonquiz.api

import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsChannel
import jp.ac.it_college.std.s22001.pokemonquiz.api.model.NamedAPIResource
import jp.ac.it_college.std.s22001.pokemonquiz.api.model.Pokemon
import jp.ac.it_college.std.s22001.pokemonquiz.api.model.PokemonSpecies

object PokemonGroup {
    // ポケモンの種族情報をとる
    suspend fun getPokemonSpecies(id: Int): PokemonSpecies {
       return Client.get("/pokemon-species/$id/").body()
    }
    // [NamedApiResource]の指定でポケモンの種族情報を取る
    suspend fun getPokemonSpecies(res: NamedAPIResource): PokemonSpecies {
        if (!res.url.contains("/pokemon-species/")) {
            throw IllegalArgumentException("ポケモンの種族情報用のURLではありません")
        }
        return getPokemonSpecies(
            res.url.trim('/').split('/').last().toInt()
        )
    }
    // [id]を指定してポケモンの詳細情報を取る
    suspend fun getPokemon(id: Int): Pokemon {
        return Client.get("/pokemon/$id/").body()
    }
    // [NamedAPIResource]を指定してポケモンの詳細情報を取る
    suspend fun getPokemon(res: NamedAPIResource): Pokemon {
        if (!res.url.contains("/pokemon")) {
            throw IllegalArgumentException("ポケモン詳細用のURLではありません")
        }
        return getPokemon(
            res.url.trim('/').split('/').last().toInt()
        )
    }
}