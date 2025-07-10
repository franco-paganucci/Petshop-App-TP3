package com.example.petshopapptp3.components.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshopapptp3.R


@Composable
fun CategorySection(purple: Color) {
    val gray = Color(0xFFF6F6F6)
    val categories = listOf("Food", "Toys", "Accessories")
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Category", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("View All", color = purple, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícono
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(48.dp)
                    .background(gray, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.swap),
                    contentDescription = "Swap",
                    modifier = Modifier.size(20.dp),
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }

            categories.forEachIndexed { index, text ->
                val isSelected = selectedIndex == index
                Box(
                    modifier = Modifier
                        .height(46.dp)
                        .background(
                            color = if (isSelected) purple else gray,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { selectedIndex = index }
                        .padding(horizontal = 16.dp, vertical = 1.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        color = if (isSelected) Color.White else Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}





