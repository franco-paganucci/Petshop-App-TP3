package com.example.petshopapptp3.screens.homepage.notification.notificationscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.petshopapptp3.components.homePage.notification.NotificationData
import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.homePage.notification.NotificationList
import com.example.petshopapptp3.components.paymentMethod.ArrowTitle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }

    val tabTitles = listOf("Activity", "Seller Mode")

    val activityItems = List(4) {
        NotificationData("SALE 50%", "Check the details!", R.drawable.comida_canina)
    }

    val sellerItems = listOf(
        NotificationData("You Got New Order!", "Please arrange delivery", R.drawable.comida_canina),
        NotificationData("Momon", "Liked your Product", R.drawable.avatar1),
        NotificationData("Ola", "Liked your Product", R.drawable.avatar2),
        NotificationData("Raul", "Liked your Product", R.drawable.avatar3),
        NotificationData("You Got New Order!", "Please arrange delivery", R.drawable.comida_canina),
        NotificationData("Vito", "Liked your Product", R.drawable.avatar4),
    )

    Scaffold(
        topBar = {
            ArrowTitle("Notification")
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(color = Color(0xFFF1F1F1), shape = MaterialTheme.shapes.large),
                verticalAlignment = Alignment.CenterVertically
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable { selectedTab = index }
                            .background(
                                color = if (selectedTab == index) Color(0xFF7C3AED) else Color.Transparent,
                                shape = MaterialTheme.shapes.large
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = if (selectedTab == index) Color.White else Color.Gray,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            val items = if (selectedTab == 0) activityItems else sellerItems

            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                items(items) { item ->
                    NotificationList(item)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}
