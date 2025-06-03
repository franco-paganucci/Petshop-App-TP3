package com.example.petshopapptp3.screens.homepage.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petshopapptp3.viewmodel.ProductViewModel
import androidx.compose.foundation.lazy.items
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.petshopapptp3.components.homePage.BestSellerHeader
import com.example.petshopapptp3.components.homePage.CategorySection
import com.example.petshopapptp3.components.homePage.PromoCard
import com.example.petshopapptp3.components.shared.HomeTopBar
import com.example.petshopapptp3.components.shared.ProductRow
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.screens.homepage.location.Location


@Composable
fun HomeScreen(navController: NavController) {
    val purple = Color(0xFF7B61FF)
    val viewModel: ProductViewModel = hiltViewModel()
    val products by viewModel.products.collectAsState()

    var showLocationModal by remember { mutableStateOf(false) }

    if (showLocationModal) {
        Location(onDismiss = { showLocationModal = false })
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { HomeTopBar(navController, onLocationClick = { showLocationModal = true }) }
        item { PromoCard(purple) }
        item { CategorySection(purple) }
        item { BestSellerHeader(purple) {
            navController.navigate(Screen.BestSeller.route)
        } }
        items(products.take(6).chunked(2)) { rowProducts ->
            ProductRow(rowProducts = rowProducts, purple = purple, navController = navController)
        }
        item { Spacer(modifier = Modifier.height(24.dp)) }
    }
}




@Preview(showBackground = true)
@Composable
fun ShowHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
