package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.PipoGoldStar
import com.example.ui.theme.PipoNeonGreen
import com.example.ui.theme.PipoPrimaryGreen
import com.example.ui.theme.PipoPurple
import com.example.ui.theme.PipoSkyBlue
import com.example.ui.theme.PipoSoftCoral
import kotlin.random.Random

data class Particle(
    val startXRatio: Float,
    val startYRatio: Float,
    val velocityX: Float,
    val velocityY: Float,
    val color: Color,
    val size: Float,
    val isCircle: Boolean
)

@Composable
fun ConfettiEffect(
    trigger: Boolean,
    modifier: Modifier = Modifier
) {
    if (!trigger) return

    val progress = remember { Animatable(0f) }

    val particles = remember {
        val colors = listOf(
            PipoNeonGreen, PipoPrimaryGreen, PipoGoldStar,
            PipoSkyBlue, PipoPurple, PipoSoftCoral
        )
        val list = mutableListOf<Particle>()
        // Burst from left and right edges
        repeat(24) { i ->
            val fromLeft = i % 2 == 0
            list.add(
                Particle(
                    startXRatio = if (fromLeft) 0.05f else 0.95f,
                    startYRatio = Random.nextFloat() * 0.4f + 0.25f,
                    velocityX = if (fromLeft) Random.nextFloat() * 280f + 120f else -(Random.nextFloat() * 280f + 120f),
                    velocityY = Random.nextFloat() * 240f - 80f,
                    color = colors[Random.nextInt(colors.size)],
                    size = Random.nextFloat() * 10f + 8f,
                    isCircle = Random.nextBoolean()
                )
            )
        }
        list
    }

    LaunchedEffect(trigger) {
        progress.snapTo(0f)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1100, easing = FastOutSlowInEasing)
        )
    }

    if (progress.value < 1f) {
        Canvas(modifier = modifier.fillMaxSize()) {
            val alpha = (1f - progress.value).coerceIn(0f, 1f)
            val p = progress.value

            particles.forEach { pt ->
                val x = (pt.startXRatio * size.width) + (pt.velocityX * p)
                val y = (pt.startYRatio * size.height) + (pt.velocityY * p) + (180f * p * p) // gravity

                val colorWithAlpha = pt.color.copy(alpha = alpha)

                if (pt.isCircle) {
                    drawCircle(
                        color = colorWithAlpha,
                        radius = pt.size * (1f - p * 0.3f),
                        center = Offset(x, y)
                    )
                } else {
                    drawRect(
                        color = colorWithAlpha,
                        topLeft = Offset(x, y),
                        size = Size(pt.size * 1.4f, pt.size * 0.8f)
                    )
                }
            }
        }
    }
}
