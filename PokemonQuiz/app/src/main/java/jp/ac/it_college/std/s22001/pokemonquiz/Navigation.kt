package jp.ac.it_college.std.s22001.pokemonquiz

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import jp.ac.it_college.std.s22001.pokemonquiz.model.PokeQuiz
import jp.ac.it_college.std.s22001.pokemonquiz.quiz.QuizScene
import jp.ac.it_college.std.s22001.pokemonquiz.result.ResultScene
import jp.ac.it_college.std.s22001.pokemonquiz.title.TitleScene

object Destinations {
    const val TITLE = "title"
    const val GENERATION = "generation_select"
    const val QUIZ = "quiz/{order}"
    const val RESULT = "result"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeNavigation(
    navController: NavHostController = rememberNavController(),
) {
    // AppBar の文言を保持するやつ
    var titleText by remember { mutableStateOf("") }
    var quizData by remember { mutableStateOf(listOf<PokeQuiz>()) }
    var score by remember { mutableIntStateOf(0) }

    Scaffold(
        // 上部のバー
        topBar = {
            TopAppBar(title = {
                Text(text = titleText)
            })
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Destinations.TITLE,
            modifier = Modifier.padding(it)
        ) {
            composable(Destinations.TITLE) {
                titleText = ""
                TitleScene(
                    onTitleClick = {
                        navController.navigate(Destinations.GENERATION)
                    }
                )
            }
            composable(Destinations.GENERATION) {
                // 世代選択画面
                // AppBar のタイトル設定
                titleText = stringResource(id = R.string.please_select_generation)
                // score のリセット
                score = 0

                SelectGenerationScene(onGenerationSelected = { gen ->
                    quizData = generationQuizData(gen)
                    navController.navigate("quiz/0")
                })
            }
            composable(
                Destinations.QUIZ,
                // [arguments] パラメータに前のデスティネーションから受け取るデータを定義する
                arguments = listOf(navArgument("order") { type = NavType.IntType })
            ) {
                titleText = stringResource(id = R.string.who)

                // composable#arguments の定義に従って取得できるようになる。非タイプセーフ
                val order = it.arguments?.getInt("order") ?: 0
                QuizScene(quizData[order]) {
                    // 正解数のカウント
                    score += if (it) 1 else 0

                    // 次の問題番号
                    val next = order + 1
                    if (quizData.size > next) {
                        // まだ次の問題がある
                        navController.navigate("quiz/$next")
                    } else {
                        // 次の問題がないので結果画面へ
                        navController.navigate(Destinations.RESULT) {
                            popUpTo(Destinations.GENERATION)
                        }
                    }
                }
            }
            composable(Destinations.RESULT) {
                // 結果画面
                // AppBar のテキスト
                titleText = ""
                ResultScene(
                    result = score,
                    onClickGenerationButton = {
                        navController.popBackStack()
                    },
                    onCLickTitleButton = {
                        navController.popBackStack(Destinations.TITLE, false)
                    }
                )
            }
        }
    }
}

fun generationQuizData(generation: Int): List<PokeQuiz> {
    return listOf(
        PokeQuiz(
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/906.png",
            choices = listOf("ニャオハ", "ホゲータ", "クワッス", "グルトン").shuffled(),
            correct = "ニャオハ"
        ),
        PokeQuiz(
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/909.png",
            choices = listOf("ニャオハ", "ホゲータ", "クワッス", "グルトン").shuffled(),
            correct = "ホゲータ"
        ),
        PokeQuiz(
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/912.png",
            choices = listOf("ニャオハ", "ホゲータ", "クワッス", "グルトン").shuffled(),
            correct = "クワッス"
        ),
        PokeQuiz(
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/915.png",
            choices = listOf("パモ", "ニャオハ", "ホゲータ", "クワッス", "グルトン").shuffled(),
            correct = "グルトン"
        ),
        PokeQuiz(
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1002.png",
            choices = listOf("パオジオン", "ニャオハ", "ホゲータ", "クワッス", "グルトン").shuffled(),
            correct = "パオジオン"
        )
    )
}