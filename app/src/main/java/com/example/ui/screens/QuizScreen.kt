package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MascotState
import com.example.ui.components.AnswerButton
import com.example.ui.components.AnswerButtonState
import com.example.ui.components.ConfettiEffect
import com.example.ui.components.PipoMascot
import com.example.ui.components.QuizProgressBar
import com.example.ui.theme.PipoBlack
import com.example.ui.theme.PipoCardBorder
import com.example.ui.theme.PipoDarkGreen
import com.example.ui.theme.PipoFireOrange
import com.example.ui.theme.PipoGoldStar
import com.example.ui.theme.PipoGrayLight
import com.example.ui.theme.PipoNeonGreen
import com.example.ui.theme.PipoPrimaryGreen
import com.example.ui.theme.PipoWhite
import com.example.ui.viewmodel.QuizUiState

@Composable
fun QuizScreen(
    uiState: QuizUiState,
    onSelectOption: (String) -> Unit,
    onDismissExplanation: () -> Unit,
    onReadAloud: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    val question = uiState.questions.getOrNull(uiState.questionIndex)
    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize().background(PipoWhite)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header: Back button, subject icon/title, streak pill, XP counter
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("quiz_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Sair do Desafio",
                            tint = PipoBlack
                        )
                    }
                    Text(
                        text = "${uiState.currentSubject.icon} ${uiState.currentSubject.title}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = PipoBlack
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Current Streak Pill
                    if (uiState.currentStreak > 0) {
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = Color(0xFFFFEEDB),
                            border = BorderStroke(1.dp, PipoFireOrange)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "🔥", fontSize = 12.sp)
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "${uiState.currentStreak}",
                                    fontWeight = FontWeight.Black,
                                    fontSize = 12.sp,
                                    color = PipoFireOrange
                                )
                            }
                        }
                    }

                    // Session XP
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color(0xFFFFF9DB),
                        border = BorderStroke(1.dp, PipoGoldStar)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "⭐", fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = "+${uiState.sessionEarnedXp}",
                                fontWeight = FontWeight.Black,
                                fontSize = 12.sp,
                                color = PipoBlack
                            )
                        }
                    }
                }
            }

            // Top Progress Bar: Pergunta X de 10
            QuizProgressBar(
                currentQuestion = uiState.questionIndex + 1,
                totalQuestions = uiState.questions.size.coerceAtLeast(1)
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Live Pipo Mascot with Speech Bubble
            PipoMascot(
                state = uiState.mascotState,
                size = 110.dp,
                speechBubbleText = uiState.mascotQuote
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (question != null) {
                // Question Card in highlight
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .testTag("question_card"),
                    shape = RoundedCornerShape(22.dp),
                    color = PipoGrayLight,
                    border = BorderStroke(1.5.dp, Color(0xFFE2E6E4))
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (question.isAiGenerated) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFFE2F0FD)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.AutoAwesome,
                                            contentDescription = "IA",
                                            tint = Color(0xFF1976D2),
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Spacer(modifier = Modifier.width(3.dp))
                                        Text(
                                            text = "Pergunta IA Pipo",
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF1976D2)
                                        )
                                    }
                                }
                            } else {
                                Spacer(modifier = Modifier.width(1.dp))
                            }

                            // TTS Read Aloud Button
                            Surface(
                                shape = CircleShape,
                                color = PipoWhite,
                                border = BorderStroke(1.dp, PipoCardBorder),
                                modifier = Modifier
                                    .size(38.dp)
                                    .clickable { onReadAloud() }
                                    .testTag("tts_read_button")
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Rounded.VolumeUp,
                                        contentDescription = "Ouvir pergunta em voz alta",
                                        tint = PipoPrimaryGreen,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = question.question,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 20.sp,
                                lineHeight = 26.sp
                            ),
                            color = PipoBlack,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Answer Options (3 or 4 alternatives)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    question.options.forEachIndexed { idx, optionText ->
                        val letter = ('A' + idx).toString()
                        val isSelected = uiState.selectedOption == optionText

                        val buttonState = when {
                            isSelected && uiState.isCorrect == true -> AnswerButtonState.SELECTED_CORRECT
                            isSelected && uiState.isCorrect == false -> AnswerButtonState.SELECTED_WRONG
                            uiState.isAnswerLocked && optionText == question.correctAnswer && uiState.showExplanation -> AnswerButtonState.SELECTED_CORRECT
                            uiState.isAnswerLocked -> AnswerButtonState.DISABLED
                            else -> AnswerButtonState.DEFAULT
                        }

                        AnswerButton(
                            indexLetter = letter,
                            text = optionText,
                            state = buttonState,
                            enabled = !uiState.isAnswerLocked,
                            onClick = { onSelectOption(optionText) }
                        )
                    }
                }

                // Educational Explanation on Incorrect Answer
                AnimatedVisibility(
                    visible = uiState.showExplanation,
                    enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn()
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 14.dp)
                            .testTag("explanation_card"),
                        shape = RoundedCornerShape(20.dp),
                        color = Color(0xFFFFF7ED),
                        border = BorderStroke(1.5.dp, Color(0xFFFFD8A8))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "💡", fontSize = 20.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Vamos entender juntos!",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color(0xFFB45309)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = uiState.explanationText,
                                fontSize = 14.sp,
                                color = PipoBlack,
                                lineHeight = 20.sp
                            )
                            Spacer(modifier = Modifier.height(14.dp))
                            Button(
                                onClick = onDismissExplanation,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .testTag("continue_after_explanation_button"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PipoPrimaryGreen,
                                    contentColor = PipoWhite
                                ),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text(
                                    text = "Entendi! Continuar ▶",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }

        // Confetti Burst Overlay
        ConfettiEffect(trigger = uiState.triggerConfetti)
    }
}
