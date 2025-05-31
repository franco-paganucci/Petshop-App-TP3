package com.example.petshopapptp3.components.loginComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.petshopapptp3.components.shared.ClickeableText

@Composable
fun HaveAccount ( ){
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Have an account?",
            style = MaterialTheme.typography.bodySmall
        )
        ClickeableText("Login", onClick = {})
    }

}