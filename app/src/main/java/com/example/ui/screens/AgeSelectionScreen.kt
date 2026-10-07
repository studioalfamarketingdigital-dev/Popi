package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MascotState
import com.example.ui.components.PipoMascot
import com.example.ui.theme.PipoBlack
import com.example.ui.theme.PipoCardBorder
import com.example.ui.theme.PipoDarkGreen
import com.example.ui.theme.PipoGrayLight
import com.example.ui.theme.PipoNeonGreen
import com.example.ui.theme.PipoPrimaryGreen
import com.example.ui.theme.PipoWhite
import com.example.ui.viewmodel.AppScreen

data class AgeOption(
    val age: Int,
    val title: String,
    val subtitle: String,
    val emoji: String
)

@Composable
fun AgeSelectionScreen(
    currentAge: Int,
    onSelectAge: (Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    val ageOptions = listOf(
        AgeOption(6, "6 anos", "Alfabetização, cores, números e animais", "🌱"),
        AgeOption(7, "7 anos", "Primeiras somas, leitura e natureza", "☀️"),
        AgeOption(8, "8 anos", "Multiplicação, gramática e curiosidades", "⚡"),
        AgeOption(9, "9 anos", "Divisão, interpretação e ciências", "🚀"),
        AgeOption(10, "10 anos", "Problemas matemáticos e raciocínio lógico", "🧠")
    )

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PipoWhite)
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Back Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.testTag("back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Voltar",
                    tint = PipoBlack
                )
            }
            Text(
                text = "Escolha sua idade",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = PipoBlack
            )
        }

        // Mascot
        PipoMascot(
            state = MascotState.CURIOUS,
            size = 110.dp,
            speechBubbleText = "Quantos aninhos você tem?"
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Qual é a sua idade?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
            color = PipoBlack
        )
        Text(
            text = "O Pipo vai preparar desafios sob medida para você!",
            fontSize = 14.sp,
            color = Color(0xFF666666),
            modifier = Modifier.padding(top = 4.dp, bottom = 18.dp)
        )

        // Age cards
        ageOptions.forEach { item ->
            val isSelected = item.age == currentAge

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onSelectAge(item.age) }
                    .testTag("age_option_${item.age}"),
                shape = RoundedCornerShape(18.dp),
                color = if (isSelected) Color(0xFFEFFFF6) else PipoWhite,
                border = BorderStroke(
                    width = if (isSelected) 2.dp else 1.5.dp,
                    color = if (isSelected) PipoPrimaryGreen else PipoCardBorder
                ),
                shadowElevation = if (isSelected) 2.dp else 1.dp
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(46.dp),
                        shape = CircleShape,
                        color = if (isSelected) PipoNeonGreen else PipoGrayLight
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "${item.age}",
                                fontWeight = FontWeight.Black,
                                fontSize = 20.sp,
                                color = PipoBlack
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = item.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = PipoBlack
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = item.emoji, fontSize = 14.sp)
                        }
                        Text(
                            text = item.subtitle,
                            fontSize = 12.sp,
                            color = Color(0xFF555555)
                        )
                    }

                    if (isSelected) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = PipoPrimaryGreen
                        ) {
                            Text(
                                text = "ESCOLHIDO",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = PipoWhite,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}
