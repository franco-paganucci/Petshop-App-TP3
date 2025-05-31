package com.example.petshopapptp3.components.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubtitleSection(Subtitle: String) {
    Text(
        text = Subtitle,
        fontSize = 20.sp,
        color = Color.Gray,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(top = 16.dp),
        lineHeight = 20.sp
    )
}