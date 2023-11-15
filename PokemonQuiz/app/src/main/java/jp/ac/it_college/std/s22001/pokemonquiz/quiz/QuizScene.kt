package jp.ac.it_college.std.s22001.pokemonquiz.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import jp.ac.it_college.std.s22001.pokemonquiz.R
import jp.ac.it_college.std.s22001.pokemonquiz.ui.theme.PokemonQuizTheme

@Composable
fun QuizScene(
    imageUrl: String,
    choices: List<String>,
    modifier: Modifier = Modifier,
) {
    Surface(modifier) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            PokeImage(imageUrl)
            PokeNameList(choices)
        }
    }
}

// [imageUrl] にポケモンの画像がある URL を指定

@Composable
fun PokeImage(imageUrl: String, isSilhouette: Boolean = true) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(240.dp)
                .clip(RoundedCornerShape(20))
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            AsyncImage(model = imageUrl, contentDescription = "PokeImage")
        }
    }
}

@Composable
fun PokeName(name: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .padding(8.dp)
        ) {
            // 名前
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun PokeNameList(items: List<String>) {
    LazyColumn() {
        items(items.shuffled()) {
            PokeName(name = it)
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun QuizScenePreview() {
    PokemonQuizTheme {
        QuizScene(
            imageUrl = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fp16-sign-va.tiktokcdn.com%2Ftos-maliva-p-0068%2F8f221e8d1d0f4301a1f5807e594dbcfd_1698777116~tplv-photomode-zoomcover%3A720%3A720.jpeg%3Fx-expires%3D1699992000%26x-signature%3D39mmdg3Y2qWdlj%252FAgX25rJ9k4%252FQ%253D&tbnid=v3WMJLj7JFKu-M&vet=10CM0BEDMowgFqFwoTCJCU-L3BwoIDFQAAAAAdAAAAABAC..i&imgrefurl=https%3A%2F%2Fwww.tiktok.com%2Fdiscover%2F%25E8%25BB%25A2%25E3%2582%25B9%25E3%2583%25A9%25E3%2580%2580%25E3%2582%25AB%25E3%2583%25AC%25E3%2583%25A9&docid=IMcUAuOgDnfYEM&w=720&h=405&q=%E3%82%A2%E3%83%8B%E3%83%A1%E7%94%BB%E5%83%8F%E3%80%80%E3%82%AB%E3%83%AC%E3%83%A9&ved=0CM0BEDMowgFqFwoTCJCU-L3BwoIDFQAAAAAdAAAAABAC",
            choices = listOf("ニャオハ", "ホゲータ", "クワッス", "グルトン"),
            modifier = Modifier.fillMaxSize()
        )
    }
}