package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Insights
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.MascotState
import com.example.data.model.Subject
import com.example.ui.components.LevelBadgeCard
import com.example.ui.components.PipoMascot
import com.example.ui.components.PipoTopBar
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
fun HomeScreen(
    uiState: QuizUiState,
    onNavigate: (AppScreen) -> Unit,
    onToggleSound: () -> Unit,
    onStartDesafioPipo: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PipoWhite)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Header
        PipoTopBar(
            totalXp = uiState.profile.totalXp,
            streak = uiState.profile.streak,
            soundEnabled = uiState.profile.soundEnabled,
            onToggleSound = onToggleSound
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Center Mascot Presentation
        PipoMascot(
            state = uiState.mascotState,
            size = 140.dp,
            speechBubbleText = "Oi, amigo! Vamos aprender brincando?"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Slogan and Welcome
        Text(
            text = "PIPO",
            fontSize = 34.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 2.sp,
            color = PipoBlack
        )
        Text(
            text = "Aprender brincando.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = PipoDarkGreen
        )

        Spacer(modifier = Modifier.height(24.dp))

        // PRIMARY BUTTON: COMEÇAR
        Button(
            onClick = { onNavigate(AppScreen.AGE_SELECTION) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(64.dp)
                .testTag("start_quiz_button"),
            colors = ButtonDefaults.buttonColors(
                containerColor = PipoNeonGreen,
                contentColor = PipoBlack
            ),
            shape = RoundedCornerShape(22.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp, pressedElevation = 1.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Surface(
                    shape = CircleShape,
                    color = PipoBlack,
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Rounded.PlayArrow,
                            contentDescription = null,
                            tint = PipoNeonGreen,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = "COMEÇAR DESAFIO",
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp,
                    letterSpacing = 0.5.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // QUICK ACTION: DESAFIO PIPO (Mix de matérias com bônus)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clickable { onStartDesafioPipo() }
                .testTag("quick_desafio_pipo_button"),
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFFEFFFF6),
            border = BorderStroke(1.5.dp, PipoPrimaryGreen)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "🎲", fontSize = 28.sp)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Desafio Pipo Turbinado",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = PipoBlack
                    )
                    Text(
                        text = "Mistura todas as matérias com bônus de XP!",
                        fontSize = 12.sp,
                        color = PipoDarkGreen
                    )
                }
                Text(text = "Jogar ▶", fontWeight = FontWeight.Black, fontSize = 13.sp, color = PipoDarkGreen)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Level & Stats Card
        Box(modifier = Modifier.padding(horizontal = 24.dp)) {
            LevelBadgeCard(
                levelInfo = uiState.profile.levelInfo,
                currentXp = uiState.profile.totalXp,
                progressFraction = uiState.profile.progressInLevel
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Secondary Navigation Buttons (Progresso & Conquistas)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigate(AppScreen.PROGRESS) }
                    .testTag("progress_screen_button"),
                shape = RoundedCornerShape(18.dp),
                color = PipoGrayLight,
                border = BorderStroke(1.dp, PipoCardBorder)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Insights,
                        contentDescription = "Meu Progresso",
                        tint = PipoPrimaryGreen,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Meu Progresso",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = PipoBlack
                    )
                }
            }

            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigate(AppScreen.ACHIEVEMENTS) }
                    .testTag("achievements_screen_button"),
                shape = RoundedCornerShape(18.dp),
                color = PipoGrayLight,
                border = BorderStroke(1.dp, PipoCardBorder)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.EmojiEvents,
                        contentDescription = "Conquistas",
                        tint = PipoFireOrange,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Conquistas",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = PipoBlack
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}
