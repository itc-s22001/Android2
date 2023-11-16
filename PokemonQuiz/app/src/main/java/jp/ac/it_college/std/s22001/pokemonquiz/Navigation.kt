package jp.ac.it_college.std.s22001.pokemonquiz

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
                titleText = stringResource(id = R.string.please_select_generation)
                SelectGenerationScene(onGenerationSelected = { gen ->
                    quizData = generationQuizData(gen)
                    navController.navigate("quiz/0")
                })
            }
            composable(
                Destinations.QUIZ,
                arguments = listOf(navArgument("order") { type = NavType.IntType })
            ) {
                titleText = ""
                val order = it.arguments?.getInt("order") ?: 0
                QuizScene(quizData[order])
            }
            composable(Destinations.RESULT) {
                ResultScene(result = 0)
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
    )
}