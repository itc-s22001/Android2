package jp.ac.it_college.std.s22001.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.ac.it_college.std.s22001.weatherapp.api.WeatherGroup
import jp.ac.it_college.std.s22001.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                MainSince(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MainSince(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var resultText by remember {
        mutableStateOf("")
    }

    Surface(modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ElevatedButton(
                modifier = Modifier.padding(vertical = 8.dp),
                onClick = {
                    scope.launch {
                        resultText = WeatherGroup.getWeatherSpecies(2130037).list[0].main.temp.toString()
                    }
                }
            ) {
                Text(text = "API TEST")
            }
            Surface(
            ) {
                Text(text = resultText)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        MainSince()
    }
}