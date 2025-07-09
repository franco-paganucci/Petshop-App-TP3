package com.example.petshopapptp3.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petshopapptp3.ui.theme.purple
import com.example.petshopapptp3.util.ProvideWindowSize
import com.example.petshopapptp3.util.responsiveSizes // Importamos responsiveSizes

@Composable
fun StartButton(
    text: String,
    ButtonColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sizes = responsiveSizes()

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
        modifier = modifier
            .fillMaxWidth()
            .height(sizes.buttonHeight),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            fontSize = sizes.buttonFontSize,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowButton(){
     ProvideWindowSize {
        StartButton(text = "Show", onClick = { }, ButtonColor = purple)
     }
}