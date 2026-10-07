package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.model.Subject
import com.example.ui.screens.AchievementsScreen
import com.example.ui.screens.AgeSelectionScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProgressScreen
import com.example.ui.screens.QuizResultScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.SubjectSelectionScreen
import com.example.ui.theme.PipoTheme
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PipoTheme {
                val viewModel: QuizViewModel = viewModel()
                val uiState by viewModel.uiState.collectAsState()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                ) { innerPadding ->
                    PipoAppNavigation(
                        viewModel = viewModel,
                        uiState = uiState,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PipoAppNavigation(
    viewModel: QuizViewModel,
    uiState: com.example.ui.viewmodel.QuizUiState,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        targetState = uiState.currentScreen,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "screen_navigation",
        modifier = modifier
    ) { screen ->
        when (screen) {
            AppScreen.HOME -> HomeScreen(
                uiState = uiState,
                onNavigate = { viewModel.navigateTo(it) },
                onToggleSound = { viewModel.toggleSound() },
                onStartDesafioPipo = { viewModel.startQuiz(Subject.DESAFIO_PIPO) }
            )

            AppScreen.AGE_SELECTION -> AgeSelectionScreen(
                currentAge = uiState.selectedAge,
                onSelectAge = { viewModel.selectAge(it) },
                onBack = { viewModel.navigateTo(AppScreen.HOME) }
            )

            AppScreen.SUBJECT_SELECTION -> SubjectSelectionScreen(
                uiState = uiState,
                onSelectSubject = { viewModel.startQuiz(it) },
                onBack = { viewModel.navigateTo(AppScreen.AGE_SELECTION) }
            )

            AppScreen.QUIZ -> QuizScreen(
                uiState = uiState,
                onSelectOption = { viewModel.onOptionSelected(it) },
                onDismissExplanation = { viewModel.dismissExplanationAndContinue() },
                onReadAloud = { viewModel.readQuestionAloud() },
                onBack = { viewModel.navigateTo(AppScreen.SUBJECT_SELECTION) }
            )

            AppScreen.QUIZ_RESULT -> QuizResultScreen(
                uiState = uiState,
                onPlayAgain = { viewModel.startQuiz(uiState.currentSubject) },
                onChangeSubject = { viewModel.navigateTo(AppScreen.SUBJECT_SELECTION) },
                onViewProgress = { viewModel.navigateTo(AppScreen.PROGRESS) }
            )

            AppScreen.PROGRESS -> ProgressScreen(
                uiState = uiState,
                onBack = { viewModel.navigateTo(AppScreen.HOME) }
            )

            AppScreen.ACHIEVEMENTS -> AchievementsScreen(
                uiState = uiState,
                onBack = { viewModel.navigateTo(AppScreen.HOME) }
            )
        }
    }
}
