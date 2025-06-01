package com.example.petshopapptp3.components.navigationComponents

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.petshopapptp3.navigation.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Home.route) },
            label = { Text("Home") },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Cart.route) },
            label = { Text("Cart") },
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Profile.route) },
            label = { Text("Profile") },
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") }
        )
    }
}
