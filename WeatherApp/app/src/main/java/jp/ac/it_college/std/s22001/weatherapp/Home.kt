package jp.ac.it_college.std.s22001.weatherapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.ac.it_college.std.s22001.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun Home(modifier: Modifier = Modifier) {
    Surface(modifier) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun HomePreview() {
    WeatherAppTheme {
        Home(Modifier.fillMaxSize())
    }
}