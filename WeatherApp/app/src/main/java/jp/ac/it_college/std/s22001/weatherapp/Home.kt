package jp.ac.it_college.std.s22001.weatherapp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import jp.ac.it_college.std.s22001.weatherapp.api.WeatherGroup
import jp.ac.it_college.std.s22001.weatherapp.model.WeatherSpecies
import jp.ac.it_college.std.s22001.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Home(
    modifier: Modifier = Modifier,

    ) {
    val scope = rememberCoroutineScope()
//    var ame by remember { mutableStateOf<WeatherSpecies?>(null) }
//    scope.launch { WeatherApi() }

    Surface(modifier) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            DropdownMenuMap()
        }

    }
//    scope.launch { ame = ApiClient.get()}
}

//suspend fun WeatherApi(): WeatherSpecies {
//    return WeatherGroup.getWeatherSpecies(2130037)
//}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun HomePreview() {
    WeatherAppTheme {
//        Home(Modifier.fillMaxSize(),)
        DropdownMenuMap()
    }
}


@Composable
fun DropdownMenuMap() {
    val item = items
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(item.values.firstOrNull() ?: 1) }
    val scope = rememberCoroutineScope()
    var temperature by remember { mutableStateOf<String>("") }
    var Sensibletemperature by remember { mutableStateOf<String>("") }
    var atmosphericpressure by remember { mutableStateOf<String>("") }
    var humidity by remember { mutableStateOf<String>("") }
    var weathergood by remember { mutableStateOf<String>("") }
    var icon by remember { mutableStateOf<String>("") }
    var windspeed by remember { mutableStateOf<String>("") }
    var Winddirection by remember { mutableStateOf<String>("") }
    var Instantaneouswindspeed by remember { mutableStateOf<String>("") }
    var rainypercent by remember { mutableStateOf<String>("") }
    var Predictiontime by remember { mutableStateOf<String>("") }
    var timesnow by remember { mutableStateOf<String>("") }


    var takusan = mutableListOf<WeatherSpecies>()


//    for (i in 0 until WeatherGroup.getWeatherSpecies().list.size)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),


        ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                .clickable { expanded = true }
        ) {
            Text(selectedOption.toString(), modifier = Modifier.padding(16.dp))
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .background(Color.LightGray, shape = MaterialTheme.shapes.small)
            ) {
                items.forEach { (key, value) ->
                    DropdownMenuItem(text = { Text(text = value) }, onClick = {
                        selectedOption = value
                        scope.launch {
                            temperature = WeatherGroup.getWeatherSpecies(key).list[0].main.temp.toString()
                            Sensibletemperature = WeatherGroup.getWeatherSpecies(key).list[0].main.feelsLike.toString()
                            atmosphericpressure = WeatherGroup.getWeatherSpecies(key).list[0].main.pressure.toString()
                            humidity = WeatherGroup.getWeatherSpecies(key).list[0].main.humidity.toString()
                            weathergood = WeatherGroup.getWeatherSpecies(key).list[0].weather[0].main
                            icon = WeatherGroup.getWeatherSpecies(key).list[0].weather[0].icon
                            windspeed = WeatherGroup.getWeatherSpecies(key).list[0].wind.speed.toString()
                            Winddirection = WeatherGroup.getWeatherSpecies(key).list[0].wind.deg.toString()
                            Instantaneouswindspeed = WeatherGroup.getWeatherSpecies(key).list[0].wind.gust.toString()
                            rainypercent = WeatherGroup.getWeatherSpecies(key).list[0].pop.toString()
//                            timesnow = WeatherGroup.getWeatherSpecies(key).list[0].snow.toString()
                            Predictiontime = WeatherGroup.getWeatherSpecies(key).list[0].dt_txt

//                            for (i in 0 until WeatherGroup.getWeatherSpecies(key).list.size) {
//
//                            }
                        }
                        expanded = false

                    })
                }
            }
        }
//        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
//            Text(text = "天気取得")
//        }
        Text("温度:$temperature ℃")
        Text("体感気温:$Sensibletemperature ℃")
        Text("気圧:$atmosphericpressure hPa")
        Text("湿度:$humidity %")
        Text("天気:$weathergood (sunny(晴れ)Rain(雨)Clouds(くもり)Snow(雪)")
        Text("天気アイコン↓")
        Image(
            painter = rememberAsyncImagePainter("https://api.openweathermap.org/img/w/$icon.png"),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
        )
        Text("風速:$windspeed m/s")
        Text("風向:$Winddirection")
        Text("瞬間風速:$Instantaneouswindspeed kt")
        Text("降水確率:$rainypercent %")
//        Text("直近3時間の積雪量:$timesnow")
        Text("予測時刻:$Predictiontime 時間")
    }
}