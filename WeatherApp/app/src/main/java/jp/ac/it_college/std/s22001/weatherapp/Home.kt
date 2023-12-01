package jp.ac.it_college.std.s22001.weatherapp

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
    var resultText by remember { mutableStateOf<WeatherSpecies?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
                    DropdownMenuItem(text = { Text(text = value)}, onClick = {
                        selectedOption = value
                        scope.launch { resultText =  WeatherGroup.getWeatherSpecies(key)}
                        expanded = false

                    })
                }
            }
        }
        Text(text = resultText.toString())
    }
}

//@Composable
//fun DropdownMenuFromIntMapTheme() {
//    WeatherAppTheme {
//        // A surface container using the 'background' color from the theme
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            DropdownMenuMap()
//        }
//    }
//}
