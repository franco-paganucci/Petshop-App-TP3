package com.example.petshopapptp3.components.homePage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun BestSellerHeader(purple: Color, onViewAllClick: () -> Unit) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Best Seller", fontWeight = FontWeight.Bold)
            Text("View All", color = purple, fontSize = 12.sp,  modifier = Modifier.clickable { onViewAllClick() })
        }
    }
}
