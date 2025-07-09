package com.example.petshopapptp3.screens.profilePage.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.profile.ModeButton
import com.example.petshopapptp3.components.profile.StatItem
import com.example.petshopapptp3.components.profile.FilterChip
import com.example.petshopapptp3.components.shared.ProductRow
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.viewModel.ProductViewModel

@Composable
fun ProfileScreen(navController: NavController) {
    var isSellerMode by remember { mutableStateOf(false) }
    val viewModel: ProductViewModel = hiltViewModel()
    val products by viewModel.products.collectAsState()

    val backgroundColor = Color.White
    val purple = Color(0xFF7B61FF)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Header con botones de modo y settings
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        ModeButton("Profile", !isSellerMode) { isSellerMode = false }
                        ModeButton("Seller Mode", isSellerMode) { isSellerMode = true }
                    }

                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Imagen principal (avatar o logo)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    val tintColor = if (!isSellerMode) Color(0xFFCEDDEF) else Color(0xFFFFD5B3)
                    val yOffset = 50.dp

                    Image(
                        painter = painterResource(id = R.drawable.fondo_avatar),
                        contentDescription = "Fondo decorativo",
                        modifier = Modifier
                            .matchParentSize()
                            .offset(y = yOffset)
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(tintColor.copy(alpha = 0.5f))
                    )

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (!isSellerMode) {
                            Image(
                                painter = painterResource(id = R.drawable.avatar4),
                                contentDescription = "Avatar",
                                modifier = Modifier
                                    .size(100.dp)
                                    .offset(y = yOffset)
                                    .clip(CircleShape)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.pittashop_logo),
                                contentDescription = "Pittashop Logo",
                                modifier = Modifier
                                    .size(100.dp)
                                    .offset(y = yOffset)
                                    .clip(RoundedCornerShape(20.dp))
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))


                if (!isSellerMode) {
                    Text("Abduldul", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilterChip("Saved", onClick = { })
                        FilterChip("Edit Profile", onClick = {
                            navController.navigate(Screen.Account.route)
                        })
                    }
                } else {

                    Text("Pittashop", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        StatItem("109", "Followers")
                        StatItem("992", "Following")
                        StatItem("80", "Sales")
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilterChip("Product", onClick = {})
                        FilterChip("Sold" , onClick = {})
                        FilterChip("Stats" , onClick = {})
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            items(products.chunked(2)) { rowProducts ->
                ProductRow(
                    rowProducts = rowProducts,
                    purple = purple,
                    navController = navController
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

