package com.example.petshopapptp3.screens.homepage.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.petshopapptp3.data.local.CartItemEntity


@Composable
fun CartItemCard(item: CartItemEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            model = item.thumbnail,
            contentDescription = item.title,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(text = item.title)
            Text(text = "Qty: ${item.quantity}")
            Text(text = "$${item.price}")
        }
    }
}
