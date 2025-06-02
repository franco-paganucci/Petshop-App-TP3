package com.example.petshopapptp3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petshopapptp3.screens.homepage.bestSeller.BestSellerScreen
import com.example.petshopapptp3.screens.homepage.cart.CartScreen
import com.example.petshopapptp3.screens.homepage.home.HomeScreen
import com.example.petshopapptp3.screens.login.LoginScreen
import com.example.petshopapptp3.screens.login.createAccount.CreateAccount
import com.example.petshopapptp3.screens.profile.ProfileScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.CreateAccount.route) {
            var isChecked by remember { mutableStateOf(false) }

            CreateAccount(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                onTermsClick = {
                    // TODO: navegar a una pantalla de Términos
                },
                onPrivacyClick = {
                    // TODO: navegar a una pantalla de Política de Privacidad
                },
                onLoginClick = { navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.CreateAccount.route) { inclusive = true }
                } }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Cart.route) {
            CartScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(Screen.BestSeller.route) {
            BestSellerScreen(navController)
        }
    }
}
