package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.VolumeMute
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
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
import com.example.ui.theme.PipoBlack
import com.example.ui.theme.PipoDarkGreen
import com.example.ui.theme.PipoFireOrange
import com.example.ui.theme.PipoGoldStar
import com.example.ui.theme.PipoGrayLight
import com.example.ui.theme.PipoNeonGreen
import com.example.ui.theme.PipoPrimaryGreen
import com.example.ui.theme.PipoWhite

@Composable
fun PipoTopBar(
    totalXp: Int,
    streak: Int,
    soundEnabled: Boolean,
    onToggleSound: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .testTag("pipo_top_bar"),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Logo and Slogan
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(PipoNeonGreen)
                    .border(2.dp, PipoPrimaryGreen, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "P",
                    fontWeight = FontWeight.Black,
                    fontSize = 22.sp,
                    color = PipoBlack
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "PIPO",
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp,
                    color = PipoBlack,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Aprender brincando",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    color = PipoDarkGreen
                )
            }
        }

        // Stats Badges (Streak, XP, Sound)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Streak Fire Chip
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = if (streak > 0) Color(0xFFFFEEDB) else PipoGrayLight,
                border = if (streak > 0) androidx.compose.foundation.BorderStroke(1.dp, PipoFireOrange) else null
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🔥", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$streak",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = if (streak > 0) PipoFireOrange else Color(0xFF666666)
                    )
                }
            }

            // XP Star Chip
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFFFFF9DB),
                border = androidx.compose.foundation.BorderStroke(1.dp, PipoGoldStar)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "⭐", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$totalXp XP",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = PipoBlack
                    )
                }
            }

            // Sound Toggle
            IconButton(
                onClick = onToggleSound,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(PipoGrayLight)
                    .testTag("sound_toggle_button")
            ) {
                Icon(
                    imageVector = if (soundEnabled) Icons.AutoMirrored.Rounded.VolumeUp else Icons.AutoMirrored.Rounded.VolumeMute,
                    contentDescription = if (soundEnabled) "Som ligado" else "Som desligado",
                    tint = if (soundEnabled) PipoPrimaryGreen else Color(0xFF888888),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun QuizProgressBar(
    currentQuestion: Int,
    totalQuestions: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("quiz_progress_container")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pergunta $currentQuestion de $totalQuestions",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = PipoBlack
            )
            Text(
                text = "${((currentQuestion.toFloat() / totalQuestions) * 100).toInt()}%",
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = PipoDarkGreen
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Custom rounded progress bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(PipoGrayLight)
        ) {
            val progressFraction = (currentQuestion.toFloat() / totalQuestions).coerceIn(0f, 1f)
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = progressFraction)
                    .height(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(PipoNeonGreen)
            )
        }
    }
}
