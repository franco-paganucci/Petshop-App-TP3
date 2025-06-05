package com.example.petshopapptp3.screens.paymentMethod.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.components.shared.InputField
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.navigation.Screen


@Composable
fun PaymentAdd(navController: NavController, fromSettings: Boolean) {
    var cardNumber by remember { mutableStateOf("") }
    var cardName by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    var showErrors by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            ArrowTitle {
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TitleSection("Add New Payment", 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                label = "Card Number",
                value = cardNumber,
                onValueChange = { cardNumber = it },
                isError = showErrors && cardNumber.isBlank(),
                showError = showErrors
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                label = "Card Name",
                value = cardName,
                onValueChange = { cardName = it },
                isError = showErrors && cardName.isBlank(),
                showError = showErrors
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                label = "Expired",
                value = expiryDate,
                onValueChange = { expiryDate = it },
                isError = showErrors && expiryDate.isBlank(),
                showError = showErrors
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                label = "CVV",
                value = cvv,
                onValueChange = { cvv = it },
                isError = showErrors && cvv.isBlank(),
                showError = showErrors
            )
        }

        StartButton("Checkout", onClick = {
            if (cardNumber.isBlank() || cardName.isBlank() || expiryDate.isBlank() || cvv.isBlank()) {
                showErrors = true
            } else {
                showErrors = false
                if (!fromSettings) {
                    navController.navigate(Screen.PaymentChoose.route)
                } else {
                    navController.popBackStack()
                }
            }
        })
    }
}

