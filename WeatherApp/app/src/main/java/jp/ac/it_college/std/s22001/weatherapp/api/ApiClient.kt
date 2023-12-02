package jp.ac.it_college.std.s22001.weatherapp.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiClient {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private val ktor = HttpClient(CIO) {
        engine {
            endpoint {
                connectTimeout = 7000
                requestTimeout = 7000
                socketTimeout = 7000
            }
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }
    suspend fun get(endpoint : String) =
        ktor.get{ url("$BASE_URL$endpoint" )}
}
//https://api.openweathermap.org/img/w/13d.png