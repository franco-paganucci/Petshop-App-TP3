package com.example.petshopapptp3.screens.paymentMethod.success

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.SubtitleSection
import com.example.petshopapptp3.components.shared.TitleSection

@Composable
fun PaymentSucces (navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier.padding(top = 32.dp)
        ) {
            TitleSection("Payment\nSuccess!")
            Spacer(modifier = Modifier.height(10.dp))
            SubtitleSection("your order is being prepared by the shop, the courier will send it to your address")
        }

        StartButton("Go Home")
    }

}
