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
import com.example.petshopapptp3.ui.theme.purple

@Composable
fun ChangeEmail(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            ArrowTitle("Change Email") {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text("New Email")
            Spacer(modifier = Modifier.height(8.dp))
            InputSimple("Abdul")
            Spacer(modifier = Modifier.height(32.dp))
        }

        StartButton("Change Email", onClick = {
            navController.navigate(Screen.Login.route)
        },
            ButtonColor = purple
        )
    }
}
