package com.example.petshopapptp3.screens.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.SubtitleSection
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.ui.theme.purple

@Composable
fun PaymentSuccessScreen(
    navController: NavController,
    paymentType: String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(top = 32.dp)
        ) {
            TitleSection("Payment\nSuccess!")
            Spacer(modifier = Modifier.height(10.dp))
            SubtitleSection("Your order is being prepared by the shop, the courier will send it to your address")

            if (paymentType == "PayPal" || paymentType == "Bank Transfer") {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Payment instructions will be sent to your email address shortly.",
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        StartButton("Go Home", onClick = {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
                launchSingleTop = true
            }
        },
            ButtonColor = purple
        )
    }
}