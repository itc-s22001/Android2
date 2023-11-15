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
            imageUrl = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fp16-sign-va.tiktokcdn.com%2Ftos-maliva-p-0068%2F8f221e8d1d0f4301a1f5807e594dbcfd_1698777116~tplv-photomode-zoomcover%3A720%3A720.jpeg%3Fx-expires%3D1699992000%26x-signature%3D39mmdg3Y2qWdlj%252FAgX25rJ9k4%252FQ%253D&tbnid=v3WMJLj7JFKu-M&vet=10CM0BEDMowgFqFwoTCJCU-L3BwoIDFQAAAAAdAAAAABAC..i&imgrefurl=https%3A%2F%2Fwww.tiktok.com%2Fdiscover%2F%25E8%25BB%25A2%25E3%2582%25B9%25E3%2583%25A9%25E3%2580%2580%25E3%2582%25AB%25E3%2583%25AC%25E3%2583%25A9&docid=IMcUAuOgDnfYEM&w=720&h=405&q=%E3%82%A2%E3%83%8B%E3%83%A1%E7%94%BB%E5%83%8F%E3%80%80%E3%82%AB%E3%83%AC%E3%83%A9&ved=0CM0BEDMowgFqFwoTCJCU-L3BwoIDFQAAAAAdAAAAABAC",
            choices = listOf("ニャオハ", "ホゲータ", "クワッス", "グルトン").shuffled(),
            correct = "ニャオハ"
        ),
    )
}