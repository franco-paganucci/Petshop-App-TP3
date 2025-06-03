package com.example.petshopapptp3.screens.homepage.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.petshopapptp3.components.homePage.cart.CartSummary
import com.example.petshopapptp3.components.homePage.cart.CartTopBar
import com.example.petshopapptp3.viewmodel.CartViewModel

@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val cartState by cartViewModel.cart.collectAsState()
    val cartItems by cartViewModel.localCartItems.collectAsState(initial = emptyList())
    val purple = Color(0xFF7B61FF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        CartTopBar { navController.popBackStack() }

        Spacer(modifier = Modifier.height(16.dp))

        if (cartItems.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cartItems) { item ->
                    CartItemCard(item)
                }
            }

            IconButton(onClick = {
                cartViewModel.clearEverything()
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar carrito", tint = Color.Red)
            }

            val total = cartItems.sumOf { it.price * it.quantity }

            CartSummary(
                cartItems = cartItems.map {
                    com.example.petshopapptp3.components.homePage.cart.CartItem(
                        title = it.title,
                        description = "Qty: ${it.quantity}",
                        price = it.price,
                        imageUrl = it.thumbnail
                    )
                },
                purple = purple,
                totalPrice = total
            )

            Spacer(modifier = Modifier.height(8.dp))


            Button(
                onClick = {
                    cartViewModel.submitCartToApi()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Finalizar compra")
            }

        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("El carrito está vacío.")
            }
        }
    }
}
