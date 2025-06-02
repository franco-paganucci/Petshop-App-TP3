package com.example.petshopapptp3.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object BestSeller : Screen("best_seller")
    object Login : Screen("login")
    object CreateAccount : Screen("create_account")
}
