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
import androidx.navigation.NavController

import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.paymentMethod.PaymentOptionCard
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.ui.theme.purple
import com.example.petshopapptp3.util.responsiveSizes
import com.example.petshopapptp3.viewModel.CartViewModel

@Composable
fun PaymentChoose(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    val sizes = responsiveSizes()
    var selectedMethod by remember { mutableStateOf("Paypal") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = sizes.paddingHorizontal, vertical = sizes.spacerHeightLarge)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            ArrowTitle {
                navController.navigate(Screen.PaymentSuccess.route)
            }

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            TitleSection(
                title = "Add New Payment",
                fontSize = sizes.titleFontSize,
                lineHeight = sizes.titleLineHeight
            )

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            PaymentOptionCard(
                title = "Paypal",
                isSelected = selectedMethod == "Paypal",
                onClick = { selectedMethod = "Paypal" },
                enabled = true
            )

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            PaymentOptionCard(
                title = "Bank Transfer",
                isSelected = selectedMethod == "Bank Transfer",
                onClick = { selectedMethod = "Bank Transfer" },
                enabled = true
            )
        }

        Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

        StartButton(
            text = "Checkout",
            onClick = {
                cartViewModel.clearEverything()
                navController.navigate(Screen.PaymentSuccess.route)
            },
            ButtonColor = purple,
        )

        Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))
    }
}

