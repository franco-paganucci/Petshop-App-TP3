package com.example.petshopapptp3.screens.paymentMethod.choose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.paymentMethod.PaymentOptionCard
import androidx.navigation.NavController
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.viewmodel.CartViewModel

@Composable
fun PaymentChoose(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    var selectedMethod by remember { mutableStateOf("Paypal") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            ArrowTitle {
                navController.navigate(Screen.PaymentSuccess.route)
            }

            Spacer(modifier = Modifier.height(24.dp))

            TitleSection("Add New Payment", 16.sp)

            Spacer(modifier = Modifier.height(24.dp))

            PaymentOptionCard(
                title = "Paypal",
                isSelected = selectedMethod == "Paypal",
                onClick = { selectedMethod = "Paypal" },
                enabled = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            PaymentOptionCard(
                title = "Bank Transfer",
                isSelected = selectedMethod == "Bank Transfer",
                onClick = { selectedMethod = "Bank Transfer" },
                enabled = true
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        StartButton("Checkout", onClick = {
            cartViewModel.clearEverything()
            navController.navigate(Screen.PaymentSuccess.route)
        })
    }

}
