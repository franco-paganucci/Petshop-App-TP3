package com.example.petshopapptp3.components.shared


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.petshopapptp3.data.remote.Product

@Composable
fun ProductRow(rowProducts: List<Product>, purple: Color) {
    val gray = Color(0xFFF6F6F6)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        rowProducts.forEach { product ->
            ProductCard(product, purple, gray, Modifier.weight(1f))
        }
        if (rowProducts.size == 1) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


