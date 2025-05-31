package com.example.petshopapptp3.components.shared

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.petshopapptp3.ui.theme.purple

@Composable
fun ClickeableText(Text: String, onClick: () -> Unit) {
    Text(
        text = Text,
        style = MaterialTheme.typography.bodySmall.copy(
            color = purple,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.clickable { onClick ( ) }
    )
}

@Preview(showBackground = true)
@Composable
fun ShowClickeableText (){
    ClickeableText("HOLA", onClick = {})
}