package com.example.petshopapptp3.navigation

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onBoarding")
    object Login : Screen("login")
    object CreateAccount : Screen("create_account")
    object Settings: Screen("Settings")
    object SettingNotifications: Screen("settingNotificationsScreen")
    object Account: Screen ("Account")
    object Home : Screen("home")
    object Cart : Screen("cartScreen")
    object Profile : Screen("profileScreen")
    object BestSeller : Screen("best_seller")
    object Notification : Screen("notification")
    object Search : Screen("search")
    object PaymentAdd: Screen("paymentAdd")
    object PaymentChoose: Screen("paymentChoose")
    object PaymentSuccess: Screen("paymentSuccess")
    object NewPassword: Screen("newPassword")
    object ForgotPasswordEmail: Screen("forgotPasswordEmail")


    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
}
