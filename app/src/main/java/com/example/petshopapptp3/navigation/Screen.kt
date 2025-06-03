package com.example.petshopapptp3.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profileScreen")
    object BestSeller : Screen("best_seller")
    object Notification : Screen("notification")
    object Search : Screen("search")
    object PaymentAdd: Screen("paymentAdd")
    object PaymentChoose: Screen("paymentChoose")
    object PaymentSuccess: Screen("paymentSuccess")

    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
}
