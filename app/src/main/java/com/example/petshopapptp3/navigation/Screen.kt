package com.example.petshopapptp3.navigation

sealed class Screen(val route: String) {
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
    data object PaymentAdd: Screen("payment_add") {
        fun createRoute(fromSettings: Boolean) = "payment_add/$fromSettings"
    }
    data object PaymentChoose: Screen("paymentChoose")
    data object PaymentSuccess: Screen("paymentSuccess")
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
