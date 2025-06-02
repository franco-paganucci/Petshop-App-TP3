package com.example.petshopapptp3.components.paymentMethod

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun PaymentTitle (Text: String){
    Text(
        text = Text,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}