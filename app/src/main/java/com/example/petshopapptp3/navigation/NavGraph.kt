package com.example.petshopapptp3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.petshopapptp3.screens.homepage.bestSeller.BestSellerScreen
import com.example.petshopapptp3.screens.homepage.cart.CartScreen
import com.example.petshopapptp3.screens.homepage.home.HomeScreen
import com.example.petshopapptp3.screens.homepage.notification.notificationscreen.NotificationScreen
import com.example.petshopapptp3.screens.homepage.productDetail.ProductDetailScreen
import com.example.petshopapptp3.screens.homepage.search.SearchScreen
import com.example.petshopapptp3.screens.profile.ProfileScreen
import com.example.petshopapptp3.viewmodel.ProductViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route)
    {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Cart.route) { CartScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
        composable(Screen.BestSeller.route) { BestSellerScreen(navController) }
        composable(Screen.Notification.route) { NotificationScreen(navController) }
        composable(Screen.Search.route) { SearchScreen(navController) }


        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            val viewModel: ProductViewModel = hiltViewModel()
            val product = viewModel.products.collectAsState().value.find { it.id == productId }

            product?.let {
                ProductDetailScreen(product = it, navController = navController)
            }
        }
    }
}
