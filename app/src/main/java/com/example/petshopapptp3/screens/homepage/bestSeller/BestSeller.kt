package com.example.petshopapptp3.screens.homepage.bestSeller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.petshopapptp3.viewmodel.ProductViewModel
import androidx.compose.foundation.lazy.items
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.components.shared.ProductCard
import com.example.petshopapptp3.navigation.Screen

@Composable
fun BestSellerScreen(navController: NavController) {
    val viewModel: ProductViewModel = viewModel()
    val products by viewModel.products.collectAsState()
    val purple = Color(0xFF7B61FF)
    val gray = Color(0xFFF6F6F6)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ArrowTitle("Best Seller"){
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(products.chunked(2)) { rowProducts ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowProducts.forEach { product ->
                    ProductCard(
                        product = product,
                        purple = purple,
                        gray = gray,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate(Screen.ProductDetail.createRoute(product.id)) }
                    )
                }

                if (rowProducts.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}