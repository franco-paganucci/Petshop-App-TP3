package com.example.petshopapptp3.components.paymentMethod

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun PaymentTitle(
    title: String,
    fontSize: TextUnit = 20.sp,
    lineHeight: TextUnit = 24.sp
) {
    Text(
        text = title,
        fontSize = fontSize,
        lineHeight = lineHeight,
        fontWeight = FontWeight.SemiBold
    )
}
