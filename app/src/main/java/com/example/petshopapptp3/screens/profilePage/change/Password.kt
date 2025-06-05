package com.example.petshopapptp3.screens.profilePage.change

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.components.shared.InputSimple
import com.example.petshopapptp3.navigation.Screen
import java.nio.file.WatchEvent

@Composable
fun ChangePassword(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            ArrowTitle("Change Password") {
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text("New Password")
            Spacer(modifier = Modifier.height(8.dp))
            InputSimple("Abdul")

            Spacer(modifier = Modifier.height(16.dp))
            Text("Confirm Password")
            Spacer(modifier = Modifier.height(8.dp))
            InputSimple("Abdul")
        }

        StartButton("Change Password") {
            navController.navigate(Screen.Login.route)
        }
    }
}
