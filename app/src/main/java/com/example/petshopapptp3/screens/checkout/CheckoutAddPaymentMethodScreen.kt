package com.example.petshopapptp3.screens.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.components.shared.InputField
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.data.remote.dto.PaymentMethod
import com.example.petshopapptp3.ui.theme.purple
import com.example.petshopapptp3.util.ProvideWindowSize
import com.example.petshopapptp3.util.responsiveSizes
import com.example.petshopapptp3.viewModel.CartViewModel

@Composable
fun CheckoutAddPaymentMethodScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    ProvideWindowSize {
        val sizes = responsiveSizes()

        var cardNumber by remember { mutableStateOf("") }
        var cardName by remember { mutableStateOf("") }
        var expiryDate by remember { mutableStateOf("") }
        var cvv by remember { mutableStateOf("") }

        var showErrors by remember { mutableStateOf(false) }
        var showSuccessMessage by remember { mutableStateOf<String?>(null) }
        var showErrorMessage by remember { mutableStateOf<String?>(null) }
        var isSaving by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = sizes.paddingHorizontal)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                ArrowTitle(
                    Text = "Add New Card",
                    onBack = { navController.popBackStack() },
                )

                Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    TitleSection(
                        title = "Card Details",
                        fontSize = sizes.titleFontSize,
                        lineHeight = sizes.titleLineHeight
                    )
                }

                Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

                InputField(
                    label = "Card Number",
                    value = cardNumber,
                    onValueChange = { if (it.length <= 16) cardNumber = it },
                    isError = showErrors && cardNumber.length != 16,
                    showError = showErrors,
                    errorMessage = if (showErrors && cardNumber.length != 16) "Card number must be 16 digits." else null, // Custom error message
                    fontSize = sizes.inputFontSize,
                    paddingVertical = sizes.inputPaddingVertical,
                    height = sizes.inputHeight,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

                InputField(
                    label = "Card Holder Name",
                    value = cardName,
                    onValueChange = { cardName = it },
                    isError = showErrors && cardName.isBlank(),
                    showError = showErrors,
                    errorMessage = if (showErrors && cardName.isBlank()) "Card holder name cannot be empty." else null, // Custom error message
                    fontSize = sizes.inputFontSize,
                    paddingVertical = sizes.inputPaddingVertical,
                    height = sizes.inputHeight
                )

                Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

                InputField(
                    label = "Expiry Date (MM/YY)",
                    value = expiryDate,
                    onValueChange = { if (it.length <= 5) expiryDate = it },
                    isError = showErrors && (expiryDate.isBlank() || expiryDate.length != 5 || !expiryDate.contains("/")),
                    showError = showErrors,
                    errorMessage = if (showErrors && (expiryDate.isBlank() || expiryDate.length != 5 || !expiryDate.contains("/"))) "Expiry date must be in MM/YY format." else null, // Custom error message
                    fontSize = sizes.inputFontSize,
                    paddingVertical = sizes.inputPaddingVertical,
                    height = sizes.inputHeight,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

                InputField(
                    label = "CVV",
                    value = cvv,
                    onValueChange = { if (it.length <= 4) cvv = it },
                    isError = showErrors && (cvv.isBlank() || (cvv.length != 3 && cvv.length != 4)),
                    showError = showErrors,
                    errorMessage = if (showErrors && (cvv.isBlank() || (cvv.length != 3 && cvv.length != 4))) "CVV must be 3 or 4 digits." else null, // Custom error message
                    fontSize = sizes.inputFontSize,
                    paddingVertical = sizes.inputPaddingVertical,
                    height = sizes.inputHeight,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

                if (showSuccessMessage != null) {
                    Text(text = showSuccessMessage!!, color = Color.Green)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (showErrorMessage != null) {
                    Text(text = showErrorMessage!!, color = Color.Red)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            if (isSaving) {
                CircularProgressIndicator(modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally))
            } else {
                StartButton(
                    text = "Save Card",
                    onClick = {
                        showErrors = true
                        val isValidCardNumber = cardNumber.length == 16
                        val isValidCardName = cardName.isNotBlank()
                        val isValidExpiryDate = expiryDate.length == 5 && expiryDate.contains("/")
                        val isValidCvv = cvv.length == 3 || cvv.length == 4

                        if (isValidCardNumber && isValidCardName && isValidExpiryDate && isValidCvv) {
                            isSaving = true
                            val newPaymentMethod = PaymentMethod(
                                type = "Credit Card",
                                cardNumberLast4 = cardNumber.takeLast(4),
                                cardName = cardName,
                                expiryDate = expiryDate,
                            )
                            cartViewModel.savePaymentMethod(
                                paymentMethod = newPaymentMethod,
                                onSuccess = {
                                    showSuccessMessage = "Card saved successfully!"
                                    showErrorMessage = null
                                    isSaving = false
                                    navController.popBackStack()
                                },
                                onError = { error ->
                                    showErrorMessage = error
                                    showSuccessMessage = null
                                    isSaving = false
                                }
                            )
                        } else {
                            showErrorMessage = "Please correct the errors in the form."
                            showSuccessMessage = null
                        }
                    },
                    ButtonColor = purple,
                )
            }
            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))
        }
    }
}