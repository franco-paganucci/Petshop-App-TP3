package com.example.petshopapptp3.screens.homepage.bestSeller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.components.shared.ProductRow
import com.example.petshopapptp3.util.responsiveSizes
import com.example.petshopapptp3.viewModel.CartViewModel
import com.example.petshopapptp3.viewModel.ProductViewModel

@Composable
fun BestSellerScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val viewModel: ProductViewModel = viewModel()
    val products by viewModel.products.collectAsState()
    val purple = Color(0xFF7B61FF)
    val sizes = responsiveSizes()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(sizes.paddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(sizes.spacerHeightLarge)
    ) {
        item {
            ArrowTitle(
                Text = "Best Seller",
                onBack = { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))
        }

        items(products.chunked(2)) { rowProducts ->
            ProductRow(
                rowProducts = rowProducts,
                purple = purple,
                navController = navController,
                onAddToCart = { product ->
                    cartViewModel.addProductToCart(product, 1)
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))
        }
    }
}


