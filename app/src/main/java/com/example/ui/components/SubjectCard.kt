package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import com.example.data.model.Subject
import com.example.ui.theme.PipoBlack
import com.example.ui.theme.PipoCardBorder
import com.example.ui.theme.PipoDarkGreen
import com.example.ui.theme.PipoGrayLight
import com.example.ui.theme.PipoNeonGreen
import com.example.ui.theme.PipoPrimaryGreen
import com.example.ui.theme.PipoWhite

@Composable
fun SubjectCard(
    subject: Subject,
    accuracy: Int?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("subject_card_${subject.id}")
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = PipoWhite,
        border = BorderStroke(1.5.dp, if (subject == Subject.DESAFIO_PIPO) PipoNeonGreen else PipoCardBorder),
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Subject Icon Bubble
            Surface(
                modifier = Modifier.size(54.dp),
                shape = CircleShape,
                color = if (subject == Subject.DESAFIO_PIPO) PipoNeonGreen else PipoGrayLight,
                border = BorderStroke(1.dp, if (subject == Subject.DESAFIO_PIPO) PipoPrimaryGreen else Color(0xFFE2E6E4))
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = subject.icon,
                        fontSize = 28.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = subject.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = PipoBlack
                    )
                    if (subject == Subject.DESAFIO_PIPO) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = PipoDarkGreen
                        ) {
                            Text(
                                text = "BÔNUS XP",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                color = PipoNeonGreen,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = subject.description,
                    fontSize = 13.sp,
                    color = Color(0xFF555555),
                    lineHeight = 17.sp
                )

                if (accuracy != null && accuracy > 0) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "⭐ Precisão: $accuracy%",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PipoPrimaryGreen
                    )
                }
            }
        }
    }
}
