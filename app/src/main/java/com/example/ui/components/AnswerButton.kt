package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.PipoBlack
import com.example.ui.theme.PipoCardBorder
import com.example.ui.theme.PipoNeonGreen
import com.example.ui.theme.PipoPrimaryGreen
import com.example.ui.theme.PipoSoftCoral
import com.example.ui.theme.PipoWhite

enum class AnswerButtonState {
    DEFAULT,
    SELECTED_CORRECT,
    SELECTED_WRONG,
    DISABLED
}

@Composable
fun AnswerButton(
    indexLetter: String,
    text: String,
    state: AnswerButtonState,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(state) {
        if (state == AnswerButtonState.SELECTED_WRONG) {
            // Friendly slight shake
            shakeOffset.snapTo(0f)
            shakeOffset.animateTo(12f, tween(50))
            shakeOffset.animateTo(-12f, tween(50))
            shakeOffset.animateTo(8f, tween(50))
            shakeOffset.animateTo(-8f, tween(50))
            shakeOffset.animateTo(0f, tween(50))
        }
    }

    val backgroundColor by animateColorAsState(
        targetValue = when (state) {
            AnswerButtonState.SELECTED_CORRECT -> PipoNeonGreen
            AnswerButtonState.SELECTED_WRONG -> Color(0xFFFFECEC)
            AnswerButtonState.DISABLED -> Color(0xFFFAFAFA)
            AnswerButtonState.DEFAULT -> PipoWhite
        },
        animationSpec = tween(200),
        label = "btn_bg"
    )

    val borderColor = when (state) {
        AnswerButtonState.SELECTED_CORRECT -> PipoPrimaryGreen
        AnswerButtonState.SELECTED_WRONG -> PipoSoftCoral
        AnswerButtonState.DISABLED -> Color(0xFFE5E5E5)
        AnswerButtonState.DEFAULT -> PipoCardBorder
    }

    val borderWidth = if (state == AnswerButtonState.SELECTED_CORRECT || state == AnswerButtonState.SELECTED_WRONG) 2.5.dp else 1.5.dp

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .offset(x = shakeOffset.value.dp)
            .testTag("answer_option_$indexLetter")
            .clickable(enabled = enabled) { onClick() },
        shape = RoundedCornerShape(18.dp),
        color = backgroundColor,
        border = BorderStroke(borderWidth, borderColor),
        shadowElevation = if (state == AnswerButtonState.DEFAULT) 2.dp else 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Index Circle (A, B, C, D or Check / Cross)
            val badgeBg = when (state) {
                AnswerButtonState.SELECTED_CORRECT -> PipoPrimaryGreen
                AnswerButtonState.SELECTED_WRONG -> PipoSoftCoral
                else -> Color(0xFFEDF2EF)
            }
            val badgeText = when (state) {
                AnswerButtonState.SELECTED_CORRECT, AnswerButtonState.SELECTED_WRONG -> PipoWhite
                else -> PipoBlack
            }

            Surface(
                modifier = Modifier.size(36.dp),
                shape = CircleShape,
                color = badgeBg
            ) {
                Box(contentAlignment = Alignment.Center) {
                    when (state) {
                        AnswerButtonState.SELECTED_CORRECT -> {
                            Icon(
                                imageVector = Icons.Rounded.Check,
                                contentDescription = "Correto",
                                tint = PipoWhite,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        AnswerButtonState.SELECTED_WRONG -> {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Incorreto",
                                tint = PipoWhite,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        else -> {
                            Text(
                                text = indexLetter,
                                fontWeight = FontWeight.Bold,
                                color = badgeText,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 17.sp,
                    fontWeight = if (state == AnswerButtonState.SELECTED_CORRECT) FontWeight.Bold else FontWeight.Medium
                ),
                color = if (state == AnswerButtonState.DISABLED) Color(0xFF888888) else PipoBlack,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
