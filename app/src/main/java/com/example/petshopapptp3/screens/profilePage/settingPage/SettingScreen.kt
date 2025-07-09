package com.example.petshopapptp3.screens.profilePage.settingPage

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.components.profile.SettingsRow

@Composable
fun SettingsScreen(navController: NavController) {
    val settingsItems = listOf(
        "Account", "Address", "Notification", "Payment Method", "Privacy", "Security"
    )
    val helpItems = listOf("Contact Us", "FAQ")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ){
            ArrowTitle("Settings Page") {
                navController.navigate(Screen.Profile.route)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Account", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            SettingsRow("Account", R.drawable.profile) {
                navController.navigate(Screen.Account.route)
            }
            SettingsRow("Address", R.drawable.home_active) {
                // TODO: NAVIGATION
            }
            SettingsRow("Notification", R.drawable.notification) {
                navController.navigate(Screen.SettingNotifications.route)
            }
            SettingsRow("Payment Method", R.drawable.wallet) {
                navController.navigate(Screen.PaymentAdd.createRoute(true))
            }
            SettingsRow("Privacy", R.drawable.danger_circle) {
                navController.navigate(Screen.TermsAndPrivacy.route)
            }
            SettingsRow("Security", R.drawable.key) {
                navController.navigate(Screen.Security.route)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Help", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            SettingsRow("Contact Us", R.drawable.phone) {
                // TODO: CONTACT US
            }
            SettingsRow("FAQ", R.drawable.document) {
                navController.navigate(Screen.FAQ.route)
            }
        }

        // Botón Log Out anclado abajo
        Button(
            onClick = { navController.navigate(Screen.Login.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Log Out", color = Color(0xFF7B61FF))
        }
    }
}






