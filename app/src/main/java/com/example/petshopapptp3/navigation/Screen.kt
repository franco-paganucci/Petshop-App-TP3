package com.example.petshopapptp3.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object OnBoarding : Screen("onBoarding")
    data object Login : Screen("login")
    data object CreateAccount : Screen("create_account")
    data object Settings: Screen("settings")
    data object SettingNotifications: Screen("settingNotificationsScreen")
    data object Account: Screen ("account")
    data object Home : Screen("home")
    data object Cart : Screen("cartScreen")
    data object Profile : Screen("profileScreen")
    data object BestSeller : Screen("best_seller")
    data object Notification : Screen("notification")
    data object Search : Screen("search")
    data object CheckoutAddPaymentMethod: Screen("checkout_add_payment_method")
    data object Checkout: Screen("checkout")
    data object PaymentSuccess: Screen(
        route = "payment_success_screen/{paymentType}",
        arguments = listOf(navArgument("paymentType") {
            type = NavType.StringType
            defaultValue = "Unknown"
            nullable = true
        })
    ) {
        fun createRoute(paymentType: String) = "payment_success_screen/$paymentType"
    }
    data object NewPassword: Screen("newPassword")
    data object ForgotPasswordEmail: Screen("forgotPasswordEmail")
    data object TermsAndPrivacy: Screen("privacy")
    data object ChangePassword: Screen("password")
    data object ChangeEmail: Screen("email")
    data object Security: Screen("security")
    data object FAQ: Screen("faq")


    data object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
}
