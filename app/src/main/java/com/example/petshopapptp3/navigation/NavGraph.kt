package com.example.petshopapptp3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument

import com.example.petshopapptp3.screens.homepage.bestSeller.BestSellerScreen
import com.example.petshopapptp3.screens.homepage.home.HomeScreen
import com.example.petshopapptp3.screens.homepage.productDetail.ProductDetailScreen
import com.example.petshopapptp3.screens.homepage.search.SearchScreen
import com.example.petshopapptp3.screens.homepage.cart.CartScreen
import com.example.petshopapptp3.screens.homepage.notification.NotificationScreen
import com.example.petshopapptp3.screens.login.loginScreen.LoginScreen
import com.example.petshopapptp3.screens.login.createAccount.CreateAccount
import com.example.petshopapptp3.screens.login.forgotPassword.email.PasswordWithEmail
import com.example.petshopapptp3.screens.login.forgotPassword.newPassword.NewPassword
import com.example.petshopapptp3.screens.onBoardign.OnBoarding
import com.example.petshopapptp3.screens.checkout.CheckoutAddPaymentMethodScreen
import com.example.petshopapptp3.screens.checkout.CheckoutScreen
import com.example.petshopapptp3.screens.checkout.PaymentSuccessScreen
import com.example.petshopapptp3.screens.profilePage.account.AccountScreen
import com.example.petshopapptp3.screens.profilePage.change.ChangeEmail
import com.example.petshopapptp3.screens.profilePage.change.ChangePassword
import com.example.petshopapptp3.screens.profilePage.faq.FaqScreen
import com.example.petshopapptp3.screens.profilePage.privacy.PrivacyView
import com.example.petshopapptp3.screens.profilePage.profile.ProfileScreen
import com.example.petshopapptp3.screens.profilePage.security.SecurityScreen
import com.example.petshopapptp3.screens.profilePage.settingPage.SettingsScreen
import com.example.petshopapptp3.screens.profilePage.settingNotifications.SettingNotificationsScreen
import com.example.petshopapptp3.viewModel.CartViewModel
import com.example.petshopapptp3.viewModel.ProductViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.OnBoarding.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.CreateAccount.route) {
            CreateAccount(navController = navController)
        }
        composable(Screen.OnBoarding.route) { OnBoarding(navController) }
        composable(Screen.Home.route) { HomeScreen(navController, cartViewModel) }
        composable(Screen.Cart.route) { CartScreen(navController, cartViewModel) }
        composable(Screen.Profile.route) { ProfileScreen(navController, cartViewModel) }
        composable(Screen.BestSeller.route) { BestSellerScreen(navController) }
        composable(Screen.Notification.route) { NotificationScreen(navController) }
        composable(Screen.Search.route) { SearchScreen(navController) }
        composable(Screen.CheckoutAddPaymentMethod.route) {
            CheckoutAddPaymentMethodScreen(navController, cartViewModel)
        }
        composable(Screen.Checkout.route) {
            CheckoutScreen(navController, cartViewModel)
        }
        composable(
            route = Screen.PaymentSuccess.route,
            arguments = Screen.PaymentSuccess.arguments
        ) { backStackEntry ->
            val paymentType = backStackEntry.arguments?.getString("paymentType")
            PaymentSuccessScreen(navController, paymentType)
        }

        composable(Screen.NewPassword.route) { NewPassword({}, navController) }
        composable(Screen.ForgotPasswordEmail.route) { PasswordWithEmail({}, navController) }
        composable(Screen.Settings.route) { SettingsScreen(navController) }
        composable(Screen.Account.route) { AccountScreen(navController) }
        composable(Screen.SettingNotifications.route) { SettingNotificationsScreen(navController) }
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            val viewModel: ProductViewModel = hiltViewModel()
            val product = viewModel.products.collectAsState().value.find { it.id == productId }
            product?.let {
                ProductDetailScreen(product = it, navController = navController, cartViewModel)
            }
        }
        composable(Screen.TermsAndPrivacy.route) { PrivacyView(navController) }
        composable(Screen.ChangeEmail.route) { ChangeEmail(navController) }
        composable(Screen.ChangePassword.route) { ChangePassword(navController) }
        composable(Screen.Security.route) { SecurityScreen(navController) }
        composable(Screen.FAQ.route) { FaqScreen(navController) }
    }
}