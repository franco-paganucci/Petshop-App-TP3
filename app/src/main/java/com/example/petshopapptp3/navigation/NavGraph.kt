package com.example.petshopapptp3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petshopapptp3.screens.homepage.cart.CartScreen
import com.example.petshopapptp3.screens.homepage.home.HomeScreen
import com.example.petshopapptp3.screens.profile.ProfileScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route)
    {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Cart.route) { CartScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
    }
}
