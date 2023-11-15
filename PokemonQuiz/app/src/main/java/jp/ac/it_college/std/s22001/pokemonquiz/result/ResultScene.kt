package jp.ac.it_college.std.s22001.pokemonquiz.result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.ac.it_college.std.s22001.pokemonquiz.R
import jp.ac.it_college.std.s22001.pokemonquiz.ui.theme.PokemonQuizTheme

@Composable
fun ResultScene(
    result: Int,
    modifier: Modifier = Modifier
) {
    Surface(modifier) {
        Column(
            modifier = Modifier.padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.score))
            Text(text = stringResource(id = R.string.point, result))

        }

    }

}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun ResultScenePreview() {
    PokemonQuizTheme {
        ResultScene(
            result = 0,
            modifier = Modifier.fillMaxSize()
        )
    }
}