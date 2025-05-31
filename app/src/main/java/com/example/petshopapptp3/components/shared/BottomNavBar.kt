package com.example.petshopapptp3.components.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petshopapptp3.R

@Composable
fun BottomNavBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf("Home", "Chat", "Bag", "Profile")

    NavigationBar(
        containerColor = Color(0xFFF8F8F8),
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },
                icon = {
                    when (index) {
                        0 -> Icon(painter = painterResource(R.drawable.home_active), contentDescription = "Home")
                        1 -> Icon(painter = painterResource(R.drawable.time_circle), contentDescription = "Chat")
                        2 -> Icon(painter = painterResource(R.drawable.bag), contentDescription = "Bag")
                        3 -> Icon(painter = painterResource(R.drawable.profile), contentDescription = "Profile")
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowNavBar (){
    BottomNavBar(1, onItemSelected = { })
}