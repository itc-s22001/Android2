package jp.ac.it_college.std.s22001.pokemonquiz

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
            // Composable 内で使えるコルーチンスコープ
            val scope = rememberCoroutineScope()

            // 現在の処理を実施している Context を取得
            val context = LocalContext.current

            // テストのダミーデータ登録
            scope.launch {
                testDataInitializeDatabase(context)
            }
            PokemonQuizTheme {
                PokeNavigation()

            }
        }
    }
}

private suspend fun testDataInitializeDatabase(context: Context) {
    withContext(Dispatchers.IO) {
        val dao = PokeRoomDatabase.getDatabase(context).pokeDao()
        // もし既にダミーデータが入っていれば何もしないで終了する
        if (dao.findByGeneration(9).isNotEmpty()) return@withContext

        // 以下はダミーデータの登録
        dao.insertAll(
            listOf(
                Poke(
                    906,
                    9,
                    "ニャオハ",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/906.png"
                ),
                Poke(
                    909,
                    9,
                    "ホゲータ",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/909.png"
                ),
                Poke(
                    912,
                    9,
                    "クワッス",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/912.png"
                ),
                Poke(
                    915,
                    9,
                    "グルトン",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/915.png"
                ),
                Poke(
                    1002,
                    9,
                    "パオジオン",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1002.png"
                ),
                Poke(
                    1005,
                    9,
                    "トドロクツキ",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1005.png"
                ),
                Poke(
                    1006,
                    9,
                    "テツノブジン",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1006.png"
                ),
                Poke(
                    1007,
                    9,
                    "コライドン",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1007.png"
                ),
                Poke(
                    1008,
                    9,
                    "ミライドン",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1008.png"
                ),
                Poke(
                    1009,
                    9,
                    "ウネルミナモ",
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1009.png"
                ),
            )

        )
    }
}
