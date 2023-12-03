package jp.ac.it_college.std.s22001.weatherapp.api

import android.provider.UserDictionary.Words.APP_ID
import io.ktor.client.call.body
import jp.ac.it_college.std.s22001.weatherapp.api.ApiClient
import jp.ac.it_college.std.s22001.weatherapp.model.WeatherSpecies


object WeatherGroup {
    suspend fun getWeatherSpecies(id: Int): WeatherSpecies {
        return ApiClient.get("/forecast?id=$id&appid=8947d99543d60b14522abdf8409d1a6e&units=metric").body()
    }
}