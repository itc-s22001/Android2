package jp.ac.it_college.std.s22001.pokemonquiz

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import jp.ac.it_college.std.s22001.pokemonquiz.api.GamesGroup
import jp.ac.it_college.std.s22001.pokemonquiz.api.PokemonGroup
import jp.ac.it_college.std.s22001.pokemonquiz.database.PokeRoomDatabase
import jp.ac.it_college.std.s22001.pokemonquiz.database.entity.Poke
import jp.ac.it_college.std.s22001.pokemonquiz.ui.theme.PokemonQuizTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonQuizTheme {
                PokeNavigation()
            }
        }
    }
}
