package com.example.petshopapptp3.screens.profilePage.security

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.profile.SettingsRow
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.navigation.Screen

@Composable
fun SecurityScreen(navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        ArrowTitle("Security"){
            navController.popBackStack()
        }
        Text("Security", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        SettingsRow("Change Password", R.drawable.key , onClick = { navController.navigate(Screen.ChangePassword.route) })
        SettingsRow("Change Email", R.drawable.key , onClick = { navController.navigate(Screen.ChangeEmail.route) })

    }
}