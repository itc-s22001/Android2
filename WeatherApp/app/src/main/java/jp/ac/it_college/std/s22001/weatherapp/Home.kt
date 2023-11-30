package jp.ac.it_college.std.s22001.weatherapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
        Home(
            Modifier.fillMaxSize(),
        )
    }
}

val items = mapOf(
    2130037 to "北海道",
    2130658 to "青森",
    2111834 to "岩手",
    2111149 to "宮城",
    2113719 to "秋田",
    2110556 to "山形",
    2112923 to "福島",
    2111901 to "茨城",
    1849053 to "栃木(宇都宮)",
    1857843 to "群馬",
    1853226 to "埼玉",
    2113014 to "千葉",
    1850144 to "東京",
    1848354 to "神奈川",
    1855431 to "新潟",
    1849876 to "富山",
    1860243 to "石川",
    1863985 to "福井",
    1859100 to "山梨",
    1856215 to "長野",
    1863641 to "岐阜",
    1851717 to "静岡",
    1865694 to "愛知",
    1849796 to "三重",
    1853574 to "滋賀",
    1857910 to "京都",
    1853909 to "大阪",
    1859171 to "兵庫",
    1855612 to "奈良",
    1926004 to "和歌山",
    1849892 to "鳥取",
    1857550 to "島根",
    1854383 to "岡山",
    1862415 to "広島",
    1848689 to "山口",
    1850158 to "徳島",
    1851100 to "香川",
    1926099 to "愛媛",
    1859146 to "高知",
    1863967 to "福岡",
    1853303 to "佐賀",
    1856177 to "長崎",
    1858421 to "熊本",
    1854487 to "大分",
    1856717 to "宮崎",
    1860827 to "鹿児島",
    1856035 to "沖縄",
)
