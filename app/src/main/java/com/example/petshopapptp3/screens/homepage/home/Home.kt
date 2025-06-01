package com.example.petshopapptp3.screens.homepage.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petshopapptp3.R

@Composable
fun HomeScreen(navController: NavController) {
    val purple = Color(0xFF7B61FF)
    val gray = Color(0xFFF6F6F6)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Location", fontSize = 12.sp, color = Color.Gray)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Jebres, Surakarta", fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
            Row {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Notifications, contentDescription = null)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Promo card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(purple, RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.comida_canina),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16.dp)
                    .size(80.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Royal Canin\nAdult Pomeranian",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    "Get an interesting promo\nhere, without conditions",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Category
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Category", fontWeight = FontWeight.Bold)
            Text("View All", color = purple, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Food", "Toys", "Accessories").forEachIndexed { i, text ->
                Box(
                    modifier = Modifier
                        .background(
                            if (i == 0) purple else gray,
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text,
                        color = if (i == 0) Color.White else Color.Black,
                        fontSize = 12.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Best Seller
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Best Seller", fontWeight = FontWeight.Bold)
            Text("View All", color = purple, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Product cards
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            listOf(
                Pair("RC Kitten", "$20,99"),
                Pair("RC Persian", "$22,99")
            ).forEachIndexed { _, (title, price) ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .border(1.dp, gray, RoundedCornerShape(16.dp))
                        .padding(12.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.comida_felina),
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                        Text(title, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text(price, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(purple, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ShowHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
