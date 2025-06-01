package com.example.petshopapptp3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.petshopapptp3.components.navigationComponents.BottomNavigationBar
import com.example.petshopapptp3.navigation.NavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Box(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
            NavGraph(navController = navController)
        }
    }
}
