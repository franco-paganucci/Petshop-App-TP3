package com.example.petshopapptp3.components.shared

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TitleSection(title: String,  fontSize: TextUnit = 40.sp) {
    Text(
        text = title,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}