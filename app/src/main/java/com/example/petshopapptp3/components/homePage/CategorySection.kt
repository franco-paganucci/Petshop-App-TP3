package com.example.petshopapptp3.components.homePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CategorySection(purple: Color) {
    val gray = Color(0xFFF6F6F6)

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Category", fontWeight = FontWeight.Bold)
            Text("View All", color = purple, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Food", "Toys", "Accessories").forEachIndexed { i, text ->
                Box(
                    modifier = Modifier
                        .background(
                            if (i == 0) purple else gray,
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text,
                        color = if (i == 0) Color.White else Color.Black,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
