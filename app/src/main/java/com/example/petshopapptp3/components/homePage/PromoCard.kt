package com.example.petshopapptp3.components.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.petshopapptp3.R


@Composable
fun PromoCard(purple: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(purple, RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.comida_canina),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(16.dp)
                .size(80.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Royal Canin\nAdult Pomeranian",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                "Get an interesting promo\nhere, without conditions",
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}