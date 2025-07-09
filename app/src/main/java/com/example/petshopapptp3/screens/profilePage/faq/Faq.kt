package com.example.petshopapptp3.screens.profilePage.faq

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.petshopapptp3.R

@Composable
fun FaqScreen(navController: NavController) {
    val faqItems = remember {
        listOf(
            "Security",
            "Security",
            "Security",
            "Security",
            "Security"
        )
    }

    val expandedStates = remember { mutableStateListOf<Boolean>().apply { repeat(faqItems.size) { add(false) } } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(painterResource(R.drawable.back), contentDescription = "Back", modifier = Modifier.size(16.dp) )

            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "FAQ",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f)) // Para centrar el texto
            Spacer(modifier = Modifier.width(48.dp)) // Tamaño del botón de back para balancear
        }

        Spacer(modifier = Modifier.height(16.dp))

        faqItems.forEachIndexed { index, title ->
            FaqItem(
                title = title,
                isExpanded = expandedStates[index],
                onToggleExpand = {
                    expandedStates[index] = !expandedStates[index]
                }
            )
        }
    }
}

@Composable
fun FaqItem(
    title: String,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggleExpand() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Icon(
                painter = painterResource(id = if (isExpanded) R.drawable.arrow_up else R.drawable.arrow_down),
                contentDescription = "Expand",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )


        }
        if (isExpanded) {
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum sapien sodales mi sagittis hendrerit.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
