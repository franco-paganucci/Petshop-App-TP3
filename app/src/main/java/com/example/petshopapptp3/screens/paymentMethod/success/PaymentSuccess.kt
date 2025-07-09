package com.example.petshopapptp3.screens.paymentMethod.success

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.SubtitleSection
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.ui.theme.purple

@Composable
fun PaymentSuccess(navController: NavController) {
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
        }

        Spacer(modifier = Modifier.height(24.dp))

        StartButton("Go Home", onClick = {
            navController.navigate(Screen.Home.route)
        },
            ButtonColor = purple
        )
    }
}

