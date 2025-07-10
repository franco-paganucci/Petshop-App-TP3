package com.example.petshopapptp3.components.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.petshopapptp3.navigation.Screen

@Composable
fun HomeTopBar(navController: NavController,
               onLocationClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier.clickable { onLocationClick() }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Location", fontSize = 12.sp, color = Color.Gray)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
            Text("Jebres, Surakarta", fontWeight = FontWeight.Bold)
        }
        Row {
            IconButton(onClick = {navController.navigate(Screen.Search.route)}) {
                Icon(Icons.Default.Search, contentDescription = null)
            }
            IconButton(onClick = {navController.navigate(Screen.Notification.route)}) {
                Icon(Icons.Default.Notifications, contentDescription = null)
            }
        }
    }
}