package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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
import com.example.ui.components.ConfettiEffect
import com.example.ui.components.PipoMascot
import com.example.ui.theme.PipoBlack
import com.example.ui.theme.PipoCardBorder
import com.example.ui.theme.PipoDarkGreen
import com.example.ui.theme.PipoFireOrange
import com.example.ui.theme.PipoGoldStar
import com.example.ui.theme.PipoGrayLight
import com.example.ui.theme.PipoNeonGreen
import com.example.ui.theme.PipoPrimaryGreen
import com.example.ui.theme.PipoWhite
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.QuizUiState

@Composable
fun QuizResultScreen(
    uiState: QuizUiState,
    onPlayAgain: () -> Unit,
    onChangeSubject: () -> Unit,
    onViewProgress: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onChangeSubject() }

    val scrollState = rememberScrollState()
    val totalQ = uiState.questions.size.coerceAtLeast(1)
    val score = uiState.correctQuestionsCount

    Box(modifier = modifier.fillMaxSize().background(PipoWhite)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Celebrating Pipo Mascot with interactive reaction
            PipoMascot(
                state = if (uiState.didLevelUp) MascotState.CELEBRATING else MascotState.VICTORY,
                size = 140.dp,
                speechBubbleText = if (uiState.didLevelUp) {
                    "PARABÉNS! Você subiu de nível! 🚀"
                } else {
                    "Você foi sensacional!"
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // LEVEL UP SPECIAL CARD (When child levels up)
            AnimatedVisibility(
                visible = uiState.didLevelUp,
                enter = scaleIn() + fadeIn()
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(22.dp),
                    color = Color(0xFFEFFFF6),
                    border = BorderStroke(2.dp, PipoNeonGreen),
                    shadowElevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            modifier = Modifier.size(56.dp),
                            shape = CircleShape,
                            color = PipoNeonGreen
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = uiState.profile.levelInfo.icon,
                                    fontSize = 28.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "🎉 NOVO NÍVEL ALCANÇADO!",
                            fontWeight = FontWeight.Black,
                            fontSize = 16.sp,
                            color = PipoDarkGreen
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Nível ${uiState.profile.levelInfo.levelNumber} — ${uiState.profile.levelInfo.title}",
                            fontWeight = FontWeight.Black,
                            fontSize = 20.sp,
                            color = PipoBlack
                        )
                        Text(
                            text = "Seu conhecimento e dedicação estão crescendo cada vez mais!",
                            fontSize = 12.sp,
                            color = Color(0xFF555555),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            Text(
                text = "QUIZ CONCLUÍDO!",
                fontWeight = FontWeight.Black,
                fontSize = 28.sp,
                color = PipoBlack,
                letterSpacing = 1.sp
            )
            Text(
                text = "Você está evoluindo a cada dia!",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = PipoDarkGreen
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Score Summary Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("result_summary_card"),
                shape = RoundedCornerShape(22.dp),
                color = PipoGrayLight,
                border = BorderStroke(1.5.dp, PipoNeonGreen)
            ) {
                Row(
                    modifier = Modifier.padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Acertos
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🎯 Acertos", fontSize = 12.sp, color = Color(0xFF666666))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$score / $totalQ",
                            fontWeight = FontWeight.Black,
                            fontSize = 22.sp,
                            color = PipoBlack
                        )
                    }

                    // XP Ganho
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "⭐ XP Ganho", fontSize = 12.sp, color = Color(0xFF666666))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "+${uiState.sessionEarnedXp}",
                            fontWeight = FontWeight.Black,
                            fontSize = 22.sp,
                            color = PipoPrimaryGreen
                        )
                    }

                    // Maior Sequência
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "🔥 Sequência", fontSize = 12.sp, color = Color(0xFF666666))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${uiState.maxStreakInSession}",
                            fontWeight = FontWeight.Black,
                            fontSize = 22.sp,
                            color = PipoFireOrange
                        )
                    }
                }
            }

            // Unlocked Achievements Banner if any
            if (uiState.newlyUnlockedAchievements.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    color = Color(0xFFFFF9DB),
                    border = BorderStroke(1.5.dp, PipoGoldStar)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🎉 NOVA CONQUISTA DESBLOQUEADA!",
                            fontWeight = FontWeight.Black,
                            fontSize = 13.sp,
                            color = Color(0xFF996500)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        uiState.newlyUnlockedAchievements.forEach { ach ->
                            Row(
                                modifier = Modifier.padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = ach.icon, fontSize = 24.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = ach.title,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = PipoBlack
                                    )
                                    Text(
                                        text = ach.description,
                                        fontSize = 12.sp,
                                        color = Color(0xFF666666)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action Buttons
            Button(
                onClick = onPlayAgain,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("play_again_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PipoNeonGreen,
                    contentColor = PipoBlack
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(
                    text = "Jogar Novamente 🔄",
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onChangeSubject,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("change_subject_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PipoGrayLight,
                    contentColor = PipoBlack
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text(
                    text = "Outra Matéria 📚",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = onViewProgress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("view_progress_result_button"),
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(1.5.dp, PipoPrimaryGreen)
            ) {
                Text(
                    text = "Ver Meu Progresso 📈",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = PipoDarkGreen
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }

        ConfettiEffect(trigger = uiState.triggerConfetti)
    }
}
