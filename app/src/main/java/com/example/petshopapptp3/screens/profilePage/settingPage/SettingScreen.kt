package com.example.petshopapptp3.screens.profilePage.settingPage

import android.R.attr.onClick
import androidx.compose.runtime.Composable



import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.navigation.Screen
import androidx.compose.foundation.clickable

@Composable
fun SettingsScreen(navController: NavController) {
    val settingsItems = listOf(
        "Account", "Address", "Notification", "Payment Method", "Privacy", "Security"
    )
    val helpItems = listOf("Contact Us", "FAQ")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ArrowTitle("Settings Page")
        Spacer(modifier = Modifier.height(24.dp))

        // TODO: NAVIGATION
        Text("Account", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        SettingsRow("Account", R.drawable.profile, onClick = { })
        SettingsRow("Address", R.drawable.home_active, onClick = { })
        SettingsRow("Notification", R.drawable.notification, onClick = { })
        SettingsRow("Payment Method", R.drawable.wallet, onClick = { })
        SettingsRow("Privacy", R.drawable.danger_circle, onClick = { })
        SettingsRow("Security", R.drawable.key, onClick = { })


        Spacer(modifier = Modifier.height(16.dp))

        Text("Help", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        SettingsRow("Contact Us", R.drawable.phone , onClick = { })
        SettingsRow("FAQ", R.drawable.document, onClick = { })


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate(Screen.Login.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .height(50.dp)
        ) {
            Text("Log Out", color = Color(0xFF7B61FF))
        }
    }
}

// TODO: PONER EN UNA CARPETA POR SEPARADO
@Composable
fun SettingsRow(title: String, iconRes: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(title, fontSize = 14.sp)
        }

        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}


