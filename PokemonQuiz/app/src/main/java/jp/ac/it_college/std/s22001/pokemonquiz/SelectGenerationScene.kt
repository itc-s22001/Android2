package jp.ac.it_college.std.s22001.pokemonquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.ac.it_college.std.s22001.pokemonquiz.ui.theme.PokemonQuizTheme

@Composable
fun SelectGenerationScene(modifier: Modifier = Modifier) {
    Surface(modifier) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(1) {
                ItemGeneration(generation = 9, seriesName = "スカーレット/バイオレット")
            }

        }
    }
}

@Composable
fun ItemGeneration(generation: Int, seriesName: String) {
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.generation, generation),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = seriesName,
                style = MaterialTheme.typography.titleMedium
            )

        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun SelectGenerationScenePreview() {
    PokemonQuizTheme {
        SelectGenerationScene(Modifier.fillMaxSize())
    }
}