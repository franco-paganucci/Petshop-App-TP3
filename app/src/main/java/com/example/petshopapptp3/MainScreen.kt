package com.example.petshopapptp3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.petshopapptp3.components.navigationComponents.BottomNavigationBar
import com.example.petshopapptp3.navigation.NavGraph
import com.example.petshopapptp3.navigation.Screen
import androidx.compose.runtime.getValue

@Composable
fun MainScreen() {
    val navController = rememberNavController()


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val hideBottomBarRoutes = listOf(
        Screen.OnBoarding.route,
        Screen.Login.route,
        Screen.CreateAccount.route,
        Screen.NewPassword.route,
        Screen.ForgotPasswordEmail.route,
        Screen.Settings.route,
        Screen.PaymentAdd.route,
        Screen.PaymentChoose.route,
        Screen.PaymentSuccess.route,
        Screen.Account.route,
        Screen.TermsAndPrivacy.route,
        Screen.ChangeEmail.route,
        Screen.ChangePassword.route,
        Screen.Security.route,
        Screen.Notification.route,
        Screen.SettingNotifications.route,
        Screen.TermsAndPrivacy.route,
        Screen.FAQ.route,
        Screen.BestSeller.route,
        Screen.Search.route


    )

    val showBottomBar = currentRoute !in hideBottomBarRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavGraph(navController = navController)
        }
    }
}