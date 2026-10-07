package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.data.model.Subject
import com.example.ui.components.PipoMascot
import com.example.ui.components.SubjectCard
import com.example.ui.theme.PipoBlack
import com.example.ui.theme.PipoWhite
import com.example.ui.viewmodel.QuizUiState

@Composable
fun SubjectSelectionScreen(
    uiState: QuizUiState,
    onSelectSubject: (Subject) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    val subjects = listOf(
        Subject.DESAFIO_PIPO,
        Subject.MATEMATICA,
        Subject.PORTUGUES,
        Subject.CIENCIAS,
        Subject.GEOGRAFIA,
        Subject.HISTORIA,
        Subject.CONHECIMENTOS_GERAIS
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
                text = "Escolha sua matéria",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = PipoBlack
            )
        }

        // Mascot
        PipoMascot(
            state = MascotState.HAPPY,
            size = 110.dp,
            speechBubbleText = "Qual aventura vamos começar?"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "O que você quer aprender?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
            color = PipoBlack
        )
        Text(
            text = "Rodada rápida com 10 perguntas interativas!",
            fontSize = 13.sp,
            color = Color(0xFF666666),
            modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)
        )

        // Subject Cards
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            subjects.forEach { subj ->
                val accuracy = uiState.profile.subjectStats[subj.id]?.accuracy
                SubjectCard(
                    subject = subj,
                    accuracy = accuracy,
                    onClick = { onSelectSubject(subj) }
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}
