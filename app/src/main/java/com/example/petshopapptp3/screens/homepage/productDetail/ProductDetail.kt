package com.example.petshopapptp3.screens.homepage.productDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import kotlinx.coroutines.launch

import coil.compose.rememberAsyncImagePainter

import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.data.remote.Product
import com.example.petshopapptp3.viewModel.CartViewModel
import com.example.petshopapptp3.util.responsiveSizes

@Composable
fun ProductDetailScreen(
    product: Product,
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val purple = Color(0xFF7B61FF)
    var quantity by remember { mutableIntStateOf(1) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val sizes = responsiveSizes()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = sizes.paddingHorizontal)
                .padding(top = paddingValues.calculateTopPadding(), bottom = paddingValues.calculateBottomPadding())
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ArrowTitle("Product Detail") {
                    navController.popBackStack()
                }
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
            }

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(sizes.imageSize)
                    .background(Color(0xFFF6F6F6)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(product.thumbnail),
                    contentDescription = product.title,
                    modifier = Modifier.size(sizes.imageSize)
                )
            }

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            Text(
                text = product.title,
                fontWeight = FontWeight.Bold,
                fontSize = sizes.titleFontSize
            )

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge / 2))

            Text(
                text = product.description,
                color = Color.Gray,
                fontSize = sizes.subtitleFontSize,
                lineHeight = sizes.subtitleLineHeight
            )

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { if (quantity > 1) quantity-- }) {
                        Icon(Icons.Default.Delete, contentDescription = "Decrease")
                    }
                    Text(
                        text = quantity.toString(),
                        fontSize = sizes.subtitleFont,
                        fontWeight = FontWeight.Medium
                    )
                    IconButton(onClick = { quantity++ }) {
                        Icon(Icons.Default.Add, contentDescription = "Increase")
                    }
                }

                Text(
                    text = "$${product.price}",
                    fontSize = sizes.titleFontSize,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            StartButton(
                text = "Add to Cart",
                ButtonColor = purple,
                modifier = Modifier.height(sizes.buttonHeight),
                onClick = {
                    cartViewModel.addProductToCart(product, quantity)
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Producto/s agregado/s al carrito")
                    }
                }
            )
        }
    }
}