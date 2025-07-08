package com.example.petshopapptp3.screens.homepage.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.petshopapptp3.components.homePage.cart.CartItem
import com.example.petshopapptp3.components.homePage.cart.CartSummary
import com.example.petshopapptp3.components.homePage.cart.CartTopBar
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.util.responsiveSizes
import com.example.petshopapptp3.viewmodel.CartViewModel

@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val sizes = responsiveSizes()
    val cartItems by cartViewModel.localCartItems.collectAsState(initial = emptyList())
    val purple = Color(0xFF7B61FF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(sizes.paddingHorizontal)
    ) {
        ArrowTitle("Cart") {
            navController.navigate(Screen.Home.route)
        }

        Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

        if (cartItems.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(sizes.spacerHeightLarge)
            ) {
                items(cartItems) { item ->
                    CartItemCard(item)
                }
            }

            IconButton(onClick = {
                cartViewModel.clearEverything()
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar carrito",
                    tint = Color.Red
                )
            }

            val total = cartItems.sumOf { it.price * it.quantity }

            CartSummary(
                cartItems = cartItems.map {
                    CartItem(
                        title = it.title,
                        description = "Qty: ${it.quantity}",
                        price = it.price,
                        imageUrl = it.thumbnail
                    )
                },
                purple = purple,
                totalPrice = total,
                navController = navController
            )

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("El carrito está vacío.")
            }
        }
    }
}

