package com.example.petshopapptp3.components.shared


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.petshopapptp3.data.remote.dto.Product
import com.example.petshopapptp3.navigation.Screen

@Composable
fun ProductRow(
    rowProducts: List<Product>,
    purple: Color,
    navController: NavController,
    onAddToCart: (Product) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        rowProducts.forEach { product ->
            ProductCard(
                product = product,
                purple = purple,
                modifier = Modifier.weight(1f),
                onClick = {
                    navController.navigate(Screen.ProductDetail.createRoute(product.id))
                },
                onAddToCart = {
                    onAddToCart(product)
                }
            )
        }
        if (rowProducts.size == 1) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}






