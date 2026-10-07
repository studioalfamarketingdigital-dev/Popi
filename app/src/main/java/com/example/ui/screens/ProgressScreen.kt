package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MascotState
import com.example.data.model.Subject
import com.example.ui.components.LevelBadgeCard
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
import com.example.ui.viewmodel.QuizUiState

@Composable
fun ProgressScreen(
    uiState: QuizUiState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    val scrollState = rememberScrollState()
    val profile = uiState.profile

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PipoWhite)
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.testTag("progress_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Voltar",
                    tint = PipoBlack
                )
            }
            Text(
                text = "Meu Progresso",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = PipoBlack
            )
        }

        // Mascot
        PipoMascot(
            state = MascotState.THINKING,
            size = 110.dp,
            speechBubbleText = "Olha só o quanto você já aprendeu!"
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Level Badge Card
        LevelBadgeCard(
            levelInfo = profile.levelInfo,
            currentXp = profile.totalXp,
            progressFraction = profile.progressInLevel
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Overview Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Card 1: Respondidas
            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(18.dp),
                color = PipoGrayLight,
                border = BorderStroke(1.dp, PipoCardBorder)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "📝 Perguntas", fontSize = 12.sp, color = Color(0xFF666666))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${profile.totalAnswered}",
                        fontWeight = FontWeight.Black,
                        fontSize = 20.sp,
                        color = PipoBlack
                    )
                }
            }

            // Card 2: Taxa de Acertos
            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(18.dp),
                color = PipoGrayLight,
                border = BorderStroke(1.dp, PipoCardBorder)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "🎯 Taxa Acertos", fontSize = 12.sp, color = Color(0xFF666666))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${profile.accuracyRate}%",
                        fontWeight = FontWeight.Black,
                        fontSize = 20.sp,
                        color = PipoPrimaryGreen
                    )
                }
            }

            // Card 3: Melhor Sequência
            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(18.dp),
                color = PipoGrayLight,
                border = BorderStroke(1.dp, PipoCardBorder)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "🔥 Melhor Fogo", fontSize = 12.sp, color = Color(0xFF666666))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${profile.bestStreak}",
                        fontWeight = FontWeight.Black,
                        fontSize = 20.sp,
                        color = PipoFireOrange
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Desempenho por Matéria
        Text(
            text = "Desempenho por Matéria",
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = PipoBlack,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(10.dp))

        val trackedSubjects = listOf(
            Subject.MATEMATICA,
            Subject.PORTUGUES,
            Subject.CIENCIAS,
            Subject.GEOGRAFIA,
            Subject.HISTORIA,
            Subject.CONHECIMENTOS_GERAIS
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            trackedSubjects.forEach { subj ->
                val stat = profile.subjectStats[subj.id]
                val answered = stat?.answered ?: 0
                val correct = stat?.correct ?: 0
                val accuracy = stat?.accuracy ?: 0

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = PipoWhite,
                    border = BorderStroke(1.dp, PipoCardBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = subj.icon, fontSize = 22.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = subj.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = PipoBlack
                                )
                                Text(
                                    text = if (answered > 0) "$accuracy%" else "Ainda não jogou",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp,
                                    color = if (answered > 0) PipoDarkGreen else Color(0xFF888888)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            // Bar
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(PipoGrayLight)
                            ) {
                                if (answered > 0) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(fraction = (accuracy / 100f).coerceIn(0.04f, 1f))
                                            .height(8.dp)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(PipoPrimaryGreen)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Adaptive Tips Card
        Spacer(modifier = Modifier.height(18.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            color = Color(0xFFEFFFF6),
            border = BorderStroke(1.dp, PipoPrimaryGreen)
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "🌱", fontSize = 24.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Dica inteligente do Pipo",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = PipoDarkGreen
                    )
                    Text(
                        text = if (profile.weaknesses.isNotEmpty()) {
                            "Vamos praticar mais ${profile.weaknesses.last()} no próximo desafio para você ficar ainda mais craque!"
                        } else {
                            "Continue jogando para desbloquear todas as 8 conquistas e virar um Gênio Pipo!"
                        },
                        fontSize = 12.sp,
                        color = PipoBlack
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}
