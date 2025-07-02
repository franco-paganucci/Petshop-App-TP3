package com.example.petshopapptp3.components.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubtitleSection(
    subtitle: String,
    fontSize: TextUnit = 20.sp,
    lineHeight: TextUnit = fontSize * 1.4f,
    textAlign: TextAlign = TextAlign.Justify
) {
    Text(
        text = subtitle,
        fontSize = fontSize,
        lineHeight = lineHeight,
        color = Color.Gray,
        style = MaterialTheme.typography.bodySmall,
        textAlign = textAlign,
        modifier = Modifier.padding(top = 16.dp)
    )
}
