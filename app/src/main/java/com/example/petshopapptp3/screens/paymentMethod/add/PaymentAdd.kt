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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.paymentMethod.ArrowTitle
import com.example.petshopapptp3.components.paymentMethod.PaymentTitle
import com.example.petshopapptp3.components.shared.InputField
import com.example.petshopapptp3.components.shared.TitleSection


@Composable
fun PaymentAdd(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArrowTitle("")
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) { TitleSection("Add New Payment", 16.sp) }
        Spacer(modifier = Modifier.height(16.dp))
        InputField("Card Number")
        Spacer(modifier = Modifier.height(16.dp))
        InputField("Card Name")
        Spacer(modifier = Modifier.height(16.dp))
        InputField("Expired")
        Spacer(modifier = Modifier.height(16.dp))
        InputField("CVV")
        Spacer(modifier = Modifier.height(350.dp))
        StartButton("Checkout", onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ShowAdd (){
    PaymentAdd()
}