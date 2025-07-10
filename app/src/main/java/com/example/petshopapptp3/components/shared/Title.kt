package com.example.petshopapptp3.components.shared

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TitleSection(
    title: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 40.sp,
    lineHeight: TextUnit = fontSize * 1.3f,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = title,
        fontSize = fontSize,
        lineHeight = lineHeight,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = textAlign,
        modifier = modifier
    )
}


