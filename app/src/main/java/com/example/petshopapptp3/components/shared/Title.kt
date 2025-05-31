package com.example.petshopapptp3.components.shared

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TitleSection(Title: String) {
    Text(
        text = Title,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}