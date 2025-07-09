package com.example.petshopapptp3.components.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.petshopapptp3.components.paymentMethod.PaymentTitle
import com.example.petshopapptp3.util.responsiveSizes

@Composable
fun ArrowTitle(
    Text: String = "Payment Method",
    onBack: () -> Unit = {}
) {
    val sizes = responsiveSizes()
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
        Spacer(modifier = Modifier.width(8.dp))
        PaymentTitle(title = Text, fontSize = sizes.titleFontSize, lineHeight = sizes.titleLineHeight)
    }
}

