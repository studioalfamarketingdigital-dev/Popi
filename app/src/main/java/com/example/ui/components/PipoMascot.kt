package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MascotState
import com.example.ui.theme.PipoBlack
import com.example.ui.theme.PipoDarkGreen
import com.example.ui.theme.PipoGoldStar
import com.example.ui.theme.PipoGrayLight
import com.example.ui.theme.PipoNeonGreen
import com.example.ui.theme.PipoPrimaryGreen
import com.example.ui.theme.PipoWhite
import kotlinx.coroutines.launch

@Composable
fun PipoMascot(
    state: MascotState,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
    speechBubbleText: String? = null,
    onMascotTapped: (() -> Unit)? = null
) {
    val coroutineScope = rememberCoroutineScope()
    val infiniteTransition = rememberInfiniteTransition(label = "mascot_anim")

    // Gentle vertical floating breathing motion
    val floatOffset by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "float"
    )

    // Antenna glow pulsation
    val antennaGlow by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.35f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "antenna_glow"
    )

    // Arms celebration wave when celebrating
    val celebrationArmWave by infiniteTransition.animateFloat(
        initialValue = -8f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(
            animation = tween(350, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "celebration_arm_wave"
    )

    // State Transition Animations (Dynamic Reactivity)
    val reactionJumpY = remember { Animatable(0f) }
    val reactionScaleX = remember { Animatable(1f) }
    val reactionScaleY = remember { Animatable(1f) }
    val reactionRotate = remember { Animatable(0f) }

    // Tap interactive reaction
    var tapQuote by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(state) {
        when (state) {
            MascotState.HAPPY -> {
                // Cheerful affirmative jump & squash-and-stretch
                reactionJumpY.snapTo(0f)
                reactionScaleY.snapTo(0.85f) // initial anticipation squash
                reactionScaleY.animateTo(1.15f, tween(140, easing = FastOutSlowInEasing))
                reactionJumpY.animateTo(-24f, tween(160, easing = FastOutSlowInEasing))
                // Affirmative head nod
                reactionRotate.animateTo(5f, tween(100))
                reactionRotate.animateTo(-5f, tween(100))
                reactionRotate.animateTo(0f, tween(100))
                reactionJumpY.animateTo(0f, spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow))
                reactionScaleY.animateTo(1f, tween(150))
            }
            MascotState.ENCOURAGING -> {
                // Sympathetic, gentle head sway and reassurance
                reactionJumpY.snapTo(0f)
                reactionRotate.snapTo(0f)
                reactionRotate.animateTo(-8f, tween(160, easing = FastOutSlowInEasing))
                reactionRotate.animateTo(8f, tween(200, easing = FastOutSlowInEasing))
                reactionRotate.animateTo(-4f, tween(160, easing = FastOutSlowInEasing))
                reactionRotate.animateTo(0f, tween(140))
            }
            MascotState.SURPRISED -> {
                // Little pop back with wide eyes
                reactionScaleX.animateTo(1.22f, tween(120, easing = FastOutSlowInEasing))
                reactionScaleY.animateTo(1.22f, tween(120, easing = FastOutSlowInEasing))
                reactionJumpY.animateTo(-14f, tween(140))
                reactionJumpY.animateTo(0f, spring(dampingRatio = Spring.DampingRatioLowBouncy))
                reactionScaleX.animateTo(1f, tween(180))
                reactionScaleY.animateTo(1f, tween(180))
            }
            MascotState.CELEBRATING, MascotState.VICTORY -> {
                // Big energetic victory bounce with spring physics
                reactionJumpY.snapTo(0f)
                reactionScaleY.animateTo(1.25f, tween(150, easing = FastOutSlowInEasing))
                reactionJumpY.animateTo(-32f, tween(180, easing = FastOutSlowInEasing))
                reactionRotate.animateTo(8f, tween(120))
                reactionRotate.animateTo(-8f, tween(120))
                reactionRotate.animateTo(0f, tween(120))
                reactionJumpY.animateTo(0f, spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow))
                reactionScaleY.animateTo(1f, tween(200))
            }
            MascotState.THINKING -> {
                // Thoughtful tilt
                reactionRotate.animateTo(-7f, tween(250, easing = FastOutSlowInEasing))
                reactionJumpY.animateTo(0f, tween(200))
            }
            MascotState.CURIOUS -> {
                // Inquisitive tilt
                reactionRotate.animateTo(9f, tween(250, easing = FastOutSlowInEasing))
                reactionJumpY.animateTo(-6f, tween(200))
            }
            else -> {
                reactionRotate.animateTo(0f, tween(200))
                reactionJumpY.animateTo(0f, tween(200))
                reactionScaleX.animateTo(1f, tween(200))
                reactionScaleY.animateTo(1f, tween(200))
            }
        }
    }

    val displayBubble = speechBubbleText ?: tapQuote

    Column(
        modifier = modifier.testTag("mascot_container"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Speech Bubble
        if (!displayBubble.isNullOrBlank()) {
            PipoSpeechBubble(
                text = displayBubble,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }

        Box(
            modifier = Modifier
                .size(size)
                .offset(y = (floatOffset + reactionJumpY.value).dp)
                .scale(scaleX = reactionScaleX.value, scaleY = reactionScaleY.value)
                .rotate(reactionRotate.value)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    coroutineScope.launch {
                        // Interactive tap jump!
                        reactionJumpY.animateTo(-22f, tween(120, easing = FastOutSlowInEasing))
                        reactionRotate.animateTo(10f, tween(90))
                        reactionRotate.animateTo(-10f, tween(90))
                        reactionRotate.animateTo(0f, tween(90))
                        reactionJumpY.animateTo(0f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))

                        val interactiveQuotes = listOf(
                            "Você é demais! ⭐",
                            "Pipo está torcendo por você! 🚀",
                            "Adoro aprender junto com você! 🌱",
                            "Vamos detonar no desafio! 🧠",
                            "Estou pronto! E você? ⚡"
                        )
                        tapQuote = interactiveQuotes.random()
                        onMascotTapped?.invoke()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(size)
                    .testTag("mascot_canvas")
            ) {
                drawPipoCharacter(
                    state = state,
                    antennaGlow = antennaGlow,
                    armWave = celebrationArmWave
                )
            }
        }
    }
}

private fun DrawScope.drawPipoCharacter(
    state: MascotState,
    antennaGlow: Float,
    armWave: Float
) {
    val w = size.width
    val h = size.height

    val centerX = w / 2f
    val centerY = h * 0.54f

    // 0. Golden Victory Aura Halo (When Celebrating or in Victory)
    if (state == MascotState.CELEBRATING || state == MascotState.VICTORY) {
        val haloRadius = w * 0.46f * antennaGlow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0x66FFD166), Color(0x1139FF88), Color(0x00FFFFFF)),
                center = Offset(centerX, centerY),
                radius = haloRadius
            ),
            radius = haloRadius,
            center = Offset(centerX, centerY)
        )
    }

    // 1. Soft Dynamic Shadow
    val shadowWidth = w * (if (state == MascotState.HAPPY || state == MascotState.VICTORY) 0.44f else 0.56f)
    drawOval(
        color = Color(0x22111111),
        topLeft = Offset(centerX - shadowWidth / 2f, h * 0.90f),
        size = Size(shadowWidth, h * 0.08f)
    )

    // 2. Antennas (Two small curved cute antennas with glowing bulbs)
    val antennaPathLeft = Path().apply {
        moveTo(centerX - w * 0.12f, centerY - h * 0.28f)
        cubicTo(
            centerX - w * 0.18f, centerY - h * 0.40f,
            centerX - w * 0.26f, centerY - h * 0.44f,
            centerX - w * 0.22f, centerY - h * 0.48f
        )
    }
    drawPath(
        path = antennaPathLeft,
        color = PipoDarkGreen,
        style = Stroke(width = w * 0.035f)
    )

    val antennaPathRight = Path().apply {
        moveTo(centerX + w * 0.12f, centerY - h * 0.28f)
        cubicTo(
            centerX + w * 0.18f, centerY - h * 0.40f,
            centerX + w * 0.26f, centerY - h * 0.44f,
            centerX + w * 0.22f, centerY - h * 0.48f
        )
    }
    drawPath(
        path = antennaPathRight,
        color = PipoDarkGreen,
        style = Stroke(width = w * 0.035f)
    )

    // Glowing Antenna Tips (Yellow / Gold with glow pulsation)
    val tipRadius = w * 0.055f * (if (state == MascotState.CURIOUS || state == MascotState.CELEBRATING || state == MascotState.HAPPY) antennaGlow else 1f)
    drawCircle(
        color = PipoGoldStar,
        radius = tipRadius,
        center = Offset(centerX - w * 0.22f, centerY - h * 0.48f)
    )
    drawCircle(
        color = PipoWhite,
        radius = tipRadius * 0.45f,
        center = Offset(centerX - w * 0.23f, centerY - h * 0.49f)
    )

    drawCircle(
        color = PipoGoldStar,
        radius = tipRadius,
        center = Offset(centerX + w * 0.22f, centerY - h * 0.48f)
    )
    drawCircle(
        color = PipoWhite,
        radius = tipRadius * 0.45f,
        center = Offset(centerX + w * 0.21f, centerY - h * 0.49f)
    )

    // 3. Cute rounded feet
    drawRoundRect(
        color = PipoPrimaryGreen,
        topLeft = Offset(centerX - w * 0.22f, centerY + h * 0.28f),
        size = Size(w * 0.16f, h * 0.11f),
        cornerRadius = CornerRadius(w * 0.08f, h * 0.055f)
    )
    drawRoundRect(
        color = PipoPrimaryGreen,
        topLeft = Offset(centerX + w * 0.06f, centerY + h * 0.28f),
        size = Size(w * 0.16f, h * 0.11f),
        cornerRadius = CornerRadius(w * 0.08f, h * 0.055f)
    )

    // 4. Arms (reacting to state & wave motion)
    when (state) {
        MascotState.CELEBRATING, MascotState.VICTORY -> {
            // Arms raised high in victory with dynamic rhythmic wave
            drawRoundRect(
                color = PipoPrimaryGreen,
                topLeft = Offset(centerX - w * 0.42f, centerY - h * 0.22f + armWave),
                size = Size(w * 0.13f, h * 0.22f),
                cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
            )
            drawRoundRect(
                color = PipoPrimaryGreen,
                topLeft = Offset(centerX + w * 0.29f, centerY - h * 0.22f - armWave),
                size = Size(w * 0.13f, h * 0.22f),
                cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
            )
        }
        MascotState.THINKING -> {
            // One arm touching chin thoughtfully
            drawRoundRect(
                color = PipoPrimaryGreen,
                topLeft = Offset(centerX - w * 0.38f, centerY),
                size = Size(w * 0.12f, h * 0.18f),
                cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
            )
            drawRoundRect(
                color = PipoPrimaryGreen,
                topLeft = Offset(centerX + w * 0.08f, centerY + h * 0.06f),
                size = Size(w * 0.16f, h * 0.12f),
                cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
            )
        }
        MascotState.ENCOURAGING -> {
            // Reassuring thumbs up / supportive arm
            drawRoundRect(
                color = PipoPrimaryGreen,
                topLeft = Offset(centerX - w * 0.41f, centerY - h * 0.06f),
                size = Size(w * 0.13f, h * 0.20f),
                cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
            )
            drawRoundRect(
                color = PipoPrimaryGreen,
                topLeft = Offset(centerX + w * 0.28f, centerY),
                size = Size(w * 0.12f, h * 0.17f),
                cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
            )
        }
        else -> {
            // Natural cozy side arms
            drawRoundRect(
                color = PipoPrimaryGreen,
                topLeft = Offset(centerX - w * 0.41f, centerY - h * 0.02f),
                size = Size(w * 0.12f, h * 0.18f),
                cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
            )
            drawRoundRect(
                color = PipoPrimaryGreen,
                topLeft = Offset(centerX + w * 0.29f, centerY - h * 0.02f),
                size = Size(w * 0.12f, h * 0.18f),
                cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
            )
        }
    }

    // 5. Main Body - Round compact creature body with bright neon gradient
    val bodyRadiusX = w * 0.35f
    val bodyRadiusY = h * 0.33f

    val bodyBrush = Brush.radialGradient(
        colors = listOf(PipoNeonGreen, PipoPrimaryGreen),
        center = Offset(centerX, centerY - h * 0.06f),
        radius = bodyRadiusX * 1.2f
    )

    drawOval(
        brush = bodyBrush,
        topLeft = Offset(centerX - bodyRadiusX, centerY - bodyRadiusY),
        size = Size(bodyRadiusX * 2, bodyRadiusY * 2)
    )

    // Body border outline for crisp modern aesthetic
    drawOval(
        color = PipoDarkGreen,
        topLeft = Offset(centerX - bodyRadiusX, centerY - bodyRadiusY),
        size = Size(bodyRadiusX * 2, bodyRadiusY * 2),
        style = Stroke(width = w * 0.022f)
    )

    // 6. Belly patch (Clean light mint / white patch)
    val bellyBrush = Brush.verticalGradient(
        colors = listOf(PipoWhite, Color(0xFFE2FBEB)),
        startY = centerY,
        endY = centerY + h * 0.22f
    )
    drawOval(
        brush = bellyBrush,
        topLeft = Offset(centerX - w * 0.18f, centerY + h * 0.03f),
        size = Size(w * 0.36f, h * 0.22f)
    )

    // 7. Cheeks (Cute rosy glow - glowing brighter when happy or celebrating)
    val cheekAlpha = if (state == MascotState.HAPPY || state == MascotState.VICTORY) 0.5f else 0.28f
    val cheekRadius = w * 0.045f
    drawCircle(
        color = Color(0xFFFF6B6B).copy(alpha = cheekAlpha),
        radius = cheekRadius,
        center = Offset(centerX - w * 0.20f, centerY + h * 0.04f)
    )
    drawCircle(
        color = Color(0xFFFF6B6B).copy(alpha = cheekAlpha),
        radius = cheekRadius,
        center = Offset(centerX + w * 0.20f, centerY + h * 0.04f)
    )

    // 8. Eyes & Expression according to state
    drawMascotFace(
        state = state,
        centerX = centerX,
        centerY = centerY,
        w = w,
        h = h
    )
}

private fun DrawScope.drawMascotFace(
    state: MascotState,
    centerX: Float,
    centerY: Float,
    w: Float,
    h: Float
) {
    val eyeY = centerY - h * 0.06f
    val leftEyeX = centerX - w * 0.13f
    val rightEyeX = centerX + w * 0.13f

    when (state) {
        MascotState.HAPPY, MascotState.CELEBRATING, MascotState.VICTORY -> {
            // Joyful curved happy eyes (^ ^)
            val leftEyeArc = Path().apply {
                moveTo(leftEyeX - w * 0.06f, eyeY + h * 0.02f)
                quadraticTo(leftEyeX, eyeY - h * 0.04f, leftEyeX + w * 0.06f, eyeY + h * 0.02f)
            }
            drawPath(leftEyeArc, color = PipoBlack, style = Stroke(width = w * 0.032f))

            val rightEyeArc = Path().apply {
                moveTo(rightEyeX - w * 0.06f, eyeY + h * 0.02f)
                quadraticTo(rightEyeX, eyeY - h * 0.04f, rightEyeX + w * 0.06f, eyeY + h * 0.02f)
            }
            drawPath(rightEyeArc, color = PipoBlack, style = Stroke(width = w * 0.032f))

            // Big smile with open happy mouth!
            val mouthPath = Path().apply {
                moveTo(centerX - w * 0.09f, centerY + h * 0.05f)
                quadraticTo(centerX, centerY + h * 0.13f, centerX + w * 0.09f, centerY + h * 0.05f)
                close()
            }
            drawPath(mouthPath, color = PipoBlack, style = Fill)
            // Little pink tongue inside smile
            drawCircle(
                color = Color(0xFFFF6584),
                radius = w * 0.038f,
                center = Offset(centerX, centerY + h * 0.095f)
            )
        }
        MascotState.ENCOURAGING -> {
            // Winking eye on left, sparkling open eye on right
            val winkPath = Path().apply {
                moveTo(leftEyeX - w * 0.06f, eyeY + h * 0.01f)
                quadraticTo(leftEyeX, eyeY - h * 0.02f, leftEyeX + w * 0.06f, eyeY + h * 0.01f)
            }
            drawPath(winkPath, color = PipoBlack, style = Stroke(width = w * 0.032f))

            // Right eye open
            drawCircle(color = PipoBlack, radius = w * 0.055f, center = Offset(rightEyeX, eyeY))
            drawCircle(color = PipoWhite, radius = w * 0.022f, center = Offset(rightEyeX - w * 0.015f, eyeY - h * 0.015f))

            // Warm reassuring smile
            val mouthPath = Path().apply {
                moveTo(centerX - w * 0.06f, centerY + h * 0.06f)
                quadraticTo(centerX, centerY + h * 0.10f, centerX + w * 0.06f, centerY + h * 0.06f)
            }
            drawPath(mouthPath, color = PipoBlack, style = Stroke(width = w * 0.028f))
        }
        MascotState.THINKING -> {
            // Thinking: pupils looking upwards-right
            drawCircle(color = PipoBlack, radius = w * 0.052f, center = Offset(leftEyeX, eyeY))
            drawCircle(color = PipoWhite, radius = w * 0.020f, center = Offset(leftEyeX + w * 0.012f, eyeY - h * 0.018f))

            drawCircle(color = PipoBlack, radius = w * 0.052f, center = Offset(rightEyeX, eyeY))
            drawCircle(color = PipoWhite, radius = w * 0.020f, center = Offset(rightEyeX + w * 0.012f, eyeY - h * 0.018f))

            // Raised eyebrow
            val browPath = Path().apply {
                moveTo(rightEyeX - w * 0.05f, eyeY - h * 0.05f)
                lineTo(rightEyeX + w * 0.05f, eyeY - h * 0.07f)
            }
            drawPath(browPath, color = PipoBlack, style = Stroke(width = w * 0.026f))

            // Curious slight mouth
            val mouthPath = Path().apply {
                moveTo(centerX - w * 0.03f, centerY + h * 0.07f)
                quadraticTo(centerX + w * 0.03f, centerY + h * 0.055f, centerX + w * 0.07f, centerY + h * 0.08f)
            }
            drawPath(mouthPath, color = PipoBlack, style = Stroke(width = w * 0.028f))
        }
        MascotState.SURPRISED -> {
            // Wide round eyes
            drawCircle(color = PipoBlack, radius = w * 0.065f, center = Offset(leftEyeX, eyeY))
            drawCircle(color = PipoWhite, radius = w * 0.028f, center = Offset(leftEyeX - w * 0.015f, eyeY - h * 0.015f))

            drawCircle(color = PipoBlack, radius = w * 0.065f, center = Offset(rightEyeX, eyeY))
            drawCircle(color = PipoWhite, radius = w * 0.028f, center = Offset(rightEyeX - w * 0.015f, eyeY - h * 0.015f))

            // Small "O" mouth
            drawCircle(
                color = PipoBlack,
                radius = w * 0.035f,
                center = Offset(centerX, centerY + h * 0.075f)
            )
        }
        else -> {
            // IDLE & CURIOUS: Big sparkling friendly eyes
            drawCircle(color = PipoBlack, radius = w * 0.056f, center = Offset(leftEyeX, eyeY))
            drawCircle(color = PipoWhite, radius = w * 0.022f, center = Offset(leftEyeX - w * 0.015f, eyeY - h * 0.015f))
            drawCircle(color = PipoWhite, radius = w * 0.010f, center = Offset(leftEyeX + w * 0.012f, eyeY + h * 0.010f))

            drawCircle(color = PipoBlack, radius = w * 0.056f, center = Offset(rightEyeX, eyeY))
            drawCircle(color = PipoWhite, radius = w * 0.022f, center = Offset(rightEyeX - w * 0.015f, eyeY - h * 0.015f))
            drawCircle(color = PipoWhite, radius = w * 0.010f, center = Offset(rightEyeX + w * 0.012f, eyeY + h * 0.010f))

            // Sweet gentle smile
            val mouthPath = Path().apply {
                moveTo(centerX - w * 0.06f, centerY + h * 0.06f)
                quadraticTo(centerX, centerY + h * 0.10f, centerX + w * 0.06f, centerY + h * 0.06f)
            }
            drawPath(mouthPath, color = PipoBlack, style = Stroke(width = w * 0.028f))
        }
    }
}

@Composable
fun PipoSpeechBubble(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = PipoWhite,
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .background(PipoWhite)
                .padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                color = PipoBlack,
                textAlign = TextAlign.Center
            )
        }
    }
}
