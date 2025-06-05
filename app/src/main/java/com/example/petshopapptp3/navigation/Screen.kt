package com.example.petshopapptp3.navigation

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onBoarding")
    object Login : Screen("login")
    object CreateAccount : Screen("create_account")
    object Settings: Screen("settings")
    object SettingNotifications: Screen("settingNotificationsScreen")
    object Account: Screen ("account")
    object Home : Screen("home")
    object Cart : Screen("cartScreen")
    object Profile : Screen("profileScreen")
    object BestSeller : Screen("best_seller")
    object Notification : Screen("notification")
    object Search : Screen("search")
    object PaymentAdd: Screen("payment_add") {
        fun createRoute(fromSettings: Boolean) = "payment_add/$fromSettings"
    }
    object PaymentChoose: Screen("paymentChoose")
    object PaymentSuccess: Screen("paymentSuccess")
    object NewPassword: Screen("newPassword")
    object ForgotPasswordEmail: Screen("forgotPasswordEmail")
    object TermsAndPrivacy: Screen("privacy")
    object ChangePassword: Screen("password")
    object ChangeEmail: Screen("email")
    object Security: Screen("security")
    object FAQ: Screen("faq")


    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
}
