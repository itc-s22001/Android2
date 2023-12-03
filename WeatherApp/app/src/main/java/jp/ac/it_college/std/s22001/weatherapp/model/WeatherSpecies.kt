package jp.ac.it_college.std.s22001.weatherapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherSpecies(
//    val message: Int,
//    val cnt: Int,
    val list: List<WeatherApiResource>,
)

@Serializable
data class WeatherApiResource(
    val main: MainResource,
    val weather: List<SeasonResource>,
    val wind: Tempest,
//    val snow: TimeResource,
    val pop: Double,
    val dt_txt: String
)

@Serializable
data class MainResource(
    val temp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
)

@Serializable
data class SeasonResource(
    val main: String,
    val icon: String,
)

@Serializable
data class Tempest(
    val speed: Double,
    val deg: Int,
    val gust: Double,
)

@Serializable
data class TimeResource(
    @SerialName("3h") val h3: Double,
)




