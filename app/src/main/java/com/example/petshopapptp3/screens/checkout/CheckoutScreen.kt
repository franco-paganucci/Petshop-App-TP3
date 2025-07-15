package com.example.petshopapptp3.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.paymentMethod.PaymentOptionCard
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.ui.theme.purple
import com.example.petshopapptp3.util.responsiveSizes
import com.example.petshopapptp3.viewModel.CartViewModel
import com.example.petshopapptp3.data.remote.dto.PaymentMethod

@Composable
fun CheckoutScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    val sizes = responsiveSizes()
    val cartTotal by cartViewModel.cartTotal.collectAsState(initial = 0.0)
    val cartItems by cartViewModel.localCartItems.collectAsState(initial = emptyList())
    val savedCreditCards by cartViewModel.paymentMethods.collectAsState()
    val checkoutState by cartViewModel.checkoutState.collectAsState()

    var selectedPaymentMethod: PaymentMethod? by remember { mutableStateOf(null) }
    var isPlacingOrder by remember { mutableStateOf(false) }
    var showCheckoutError by remember { mutableStateOf<String?>(null) }

    // static payment methods
    val paypalMethod = remember { PaymentMethod(id = "paypal_static", type = "PayPal", cardName = "PayPal") }
    val bankTransferMethod = remember { PaymentMethod(id = "bank_transfer_static", type = "Bank Transfer", cardName = "Bank Transfer") }

    // Combine all available payment methods for display
    val allPaymentOptions = remember(savedCreditCards) {
        listOf(paypalMethod, bankTransferMethod) + savedCreditCards
    }

    LaunchedEffect(allPaymentOptions) {
        if (selectedPaymentMethod == null) {
            selectedPaymentMethod = allPaymentOptions.firstOrNull { it.isDefault && it.type == "Credit Card" }
                ?: allPaymentOptions.firstOrNull { it.type == "Credit Card" }
                        ?: paypalMethod
        }
    }

    LaunchedEffect(checkoutState) {
        checkoutState?.let { result ->
            isPlacingOrder = false
            if (result.isSuccess) {
                showCheckoutError = null
                navController.navigate(Screen.PaymentSuccess.createRoute(selectedPaymentMethod?.type ?: "Unknown")) {
                    popUpTo(Screen.Cart.route) { inclusive = true }
                    launchSingleTop = true
                }
            } else if (result.isFailure) {
                showCheckoutError = result.exceptionOrNull()?.localizedMessage ?: "Failed to place order."
            }
            cartViewModel._checkoutState.value = null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = sizes.paddingHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArrowTitle("Checkout") {
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            TitleSection(
                title = "Order Summary",
                fontSize = sizes.titleFontSize,
                lineHeight = sizes.titleLineHeight
            )
            Spacer(modifier = Modifier.height(sizes.spacerHeightSmall))
            Text(
                text = "Total: $$cartTotal",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            TitleSection(
                title = "Payment Method",
                fontSize = sizes.titleFontSize,
                lineHeight = sizes.titleLineHeight
            )
            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            allPaymentOptions.forEach { method ->
                val displayTitle = when (method.type) {
                    "Credit Card" -> "${method.cardName} ending in ${method.cardNumberLast4}"
                    else -> method.type
                }
                PaymentOptionCard(
                    title = displayTitle,
                    isSelected = selectedPaymentMethod?.id == method.id,
                    onClick = { selectedPaymentMethod = method },
                    enabled = true
                )
                Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))
            }

            StartButton(
                text = "Add New Card",
                onClick = { navController.navigate(Screen.CheckoutAddPaymentMethod.route) },
                ButtonColor = Color.LightGray,
                textColor = Color.DarkGray,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            showCheckoutError?.let { message ->
                Text(
                    text = message,
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )
            }
        }

        if (isPlacingOrder) {
            CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
        } else {
            StartButton(
                text = "Place Order",
                onClick = {
                    if (selectedPaymentMethod != null) {
                        if (cartItems.isNotEmpty()) {
                            isPlacingOrder = true
                            cartViewModel.saveCheckoutToFirestore(
                                selectedPaymentMethod = selectedPaymentMethod!!,
                                onSuccess = { paymentType -> /* Handled in LaunchedEffect */ },
                                onError = { error ->
                                    showCheckoutError = error
                                    isPlacingOrder = false
                                }
                            )
                        } else {
                            showCheckoutError = "Your cart is empty. Please add items before checking out."
                        }
                    } else {
                        showCheckoutError = "Please select a payment method or add a new one."
                    }
                },
                ButtonColor = purple,
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedPaymentMethod != null && cartItems.isNotEmpty()
            )
        }
        Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))
    }
}