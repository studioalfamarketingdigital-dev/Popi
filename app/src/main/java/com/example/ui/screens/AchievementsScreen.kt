package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AchievementCategory
import com.example.data.model.DefaultAchievements
import com.example.data.model.MascotState
import com.example.ui.components.PipoMascot
import com.example.ui.theme.PipoBlack
import com.example.ui.theme.PipoCardBorder
import com.example.ui.theme.PipoDarkGreen
import com.example.ui.theme.PipoGoldStar
import com.example.ui.theme.PipoGrayLight
import com.example.ui.theme.PipoNeonGreen
import com.example.ui.theme.PipoPrimaryGreen
import com.example.ui.theme.PipoWhite
import com.example.ui.viewmodel.QuizUiState

@Composable
fun AchievementsScreen(
    uiState: QuizUiState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    val allAchievements = DefaultAchievements.getAll()
    val unlockedIds = uiState.profile.unlockedAchievements
    val scrollState = rememberScrollState()
    val categoryScrollState = rememberScrollState()

    var selectedCategory by remember { mutableStateOf(AchievementCategory.ALL) }

    val filteredAchievements = remember(selectedCategory, allAchievements) {
        if (selectedCategory == AchievementCategory.ALL) {
            allAchievements
        } else {
            allAchievements.filter { it.category == selectedCategory }
        }
    }

    val totalUnlocked = unlockedIds.size
    val totalCount = allAchievements.size
    val totalPercent = if (totalCount > 0) ((totalUnlocked.toFloat() / totalCount) * 100).toInt() else 0

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
                modifier = Modifier.testTag("achievements_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Voltar",
                    tint = PipoBlack
                )
            }
            Text(
                text = "Galeria de Conquistas",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = PipoBlack
            )
        }

        // Mascot with encouraging speech bubble
        PipoMascot(
            state = if (totalUnlocked >= 5) MascotState.CELEBRATING else MascotState.HAPPY,
            size = 110.dp,
            speechBubbleText = "Você já desbloqueou $totalUnlocked de $totalCount troféus!"
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Overall Completion Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFFFFF9DB),
            border = BorderStroke(1.5.dp, PipoGoldStar),
            shadowElevation = 2.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🏆", fontSize = 26.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Progresso Total",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = PipoBlack
                            )
                            Text(
                                text = "$totalUnlocked de $totalCount Conquistas",
                                fontSize = 12.sp,
                                color = Color(0xFF8A5A00)
                            )
                        }
                    }
                    Text(
                        text = "$totalPercent%",
                        fontWeight = FontWeight.Black,
                        fontSize = 20.sp,
                        color = Color(0xFF8A5A00)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Progress Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color(0xFFEADB9F))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = (totalPercent / 100f).coerceIn(0.02f, 1f))
                            .height(10.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(PipoPrimaryGreen)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Category Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(categoryScrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AchievementCategory.entries.forEach { cat ->
                val isSelected = cat == selectedCategory
                val chipBg by animateColorAsState(
                    targetValue = if (isSelected) PipoNeonGreen else PipoGrayLight,
                    label = "cat_chip_bg"
                )

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = chipBg,
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (isSelected) PipoPrimaryGreen else PipoCardBorder
                    ),
                    modifier = Modifier
                        .clickable { selectedCategory = cat }
                        .testTag("filter_${cat.name.lowercase()}")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = cat.icon, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = cat.title,
                            fontWeight = if (isSelected) FontWeight.Black else FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = PipoBlack
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // List of Achievements
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            filteredAchievements.forEach { ach ->
                val isUnlocked = unlockedIds.contains(ach.id)
                val progress = uiState.profile.getAchievementProgress(ach.id)
                val progressFraction = (progress.toFloat() / ach.maxProgress).coerceIn(0f, 1f)

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("achievement_card_${ach.id}"),
                    shape = RoundedCornerShape(18.dp),
                    color = if (isUnlocked) PipoWhite else Color(0xFFF9F9F9),
                    border = BorderStroke(
                        width = if (isUnlocked) 1.5.dp else 1.dp,
                        color = if (isUnlocked) PipoNeonGreen else PipoCardBorder
                    ),
                    shadowElevation = if (isUnlocked) 2.dp else 0.dp
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Icon Badge
                            Surface(
                                modifier = Modifier.size(50.dp),
                                shape = CircleShape,
                                color = if (isUnlocked) Color(0xFFE2FBEB) else Color(0xFFEEEEEE),
                                border = if (isUnlocked) BorderStroke(1.dp, PipoPrimaryGreen) else null
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    if (isUnlocked) {
                                        Text(text = ach.icon, fontSize = 26.sp)
                                    } else {
                                        Icon(
                                            imageVector = Icons.Rounded.Lock,
                                            contentDescription = "Bloqueada",
                                            tint = Color(0xFF999999),
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = ach.title,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = if (isUnlocked) PipoBlack else Color(0xFF555555)
                                    )

                                    if (isUnlocked) {
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = PipoNeonGreen
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Rounded.Check,
                                                    contentDescription = null,
                                                    tint = PipoBlack,
                                                    modifier = Modifier.size(12.dp)
                                                )
                                                Spacer(modifier = Modifier.width(2.dp))
                                                Text(
                                                    text = "CONQUISTADO",
                                                    fontWeight = FontWeight.Black,
                                                    fontSize = 10.sp,
                                                    color = PipoBlack
                                                )
                                            }
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = ach.description,
                                    fontSize = 12.sp,
                                    color = if (isUnlocked) Color(0xFF555555) else Color(0xFF888888),
                                    lineHeight = 16.sp
                                )
                            }
                        }

                        // Progress Bar & Counter
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = ach.category.title,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (isUnlocked) PipoDarkGreen else Color(0xFF888888)
                            )

                            Text(
                                text = if (isUnlocked) "Concluído!" else "$progress / ${ach.maxProgress}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isUnlocked) PipoPrimaryGreen else Color(0xFF666666)
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color(0xFFEBEBEB))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(fraction = progressFraction.coerceIn(0.02f, 1f))
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(if (isUnlocked) PipoPrimaryGreen else Color(0xFFBDBDBD))
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}
