package com.example.petshopapptp3.screens.homepage.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

import com.example.petshopapptp3.components.homePage.cart.CartItem
import com.example.petshopapptp3.components.homePage.cart.CartItemCard
import com.example.petshopapptp3.components.homePage.cart.CartSummary
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.util.responsiveSizes
import com.example.petshopapptp3.viewModel.CartViewModel

@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    val sizes = responsiveSizes()
    val cartItems by cartViewModel.localCartItems.collectAsState(initial = emptyList())
    val cartTotal by cartViewModel.cartTotal.collectAsState(initial = 0.0)
    val purple = Color(0xFF7B61FF)
    val red = Color(0xFFE53935)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = sizes.paddingHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArrowTitle("Cart") {
            navController.navigate(Screen.Home.route)
        }

        Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

        if (cartItems.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(sizes.spacerHeightSmall)
            ) {
                items(cartItems) { item ->
                    CartItemCard(item)
                }
            }

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

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
                totalPrice = cartTotal ?: 0.0,
                navController = navController // navController is passed, but CartSummary itself no longer has a checkout button.
            )

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            // Primary Checkout Button
            StartButton(
                text = "Proceed to Checkout",
                onClick = { navController.navigate(Screen.Checkout.route) },
                ButtonColor = purple,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(sizes.spacerHeightSmall)) // Smaller spacer between buttons

            // New: Clear Cart Button
            StartButton(
                text = "Clear Cart",
                onClick = { cartViewModel.clearLocalCart() },
                ButtonColor = red, // Using a distinct color for "Clear Cart"
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

        } else {
            Box(modifier = Modifier.fillMaxSize().weight(1f), contentAlignment = Alignment.Center) {
                Text("Your cart is empty.")
            }
            StartButton(
                text = "Go to Shop",
                onClick = { navController.navigate(Screen.Home.route) },
                ButtonColor = purple,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))
        }
    }
}