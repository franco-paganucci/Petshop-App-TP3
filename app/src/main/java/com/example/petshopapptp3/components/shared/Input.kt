package com.example.petshopapptp3.components.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.petshopapptp3.ui.theme.purple

@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    isError: Boolean = false,
    showError: Boolean = false,
    fontSize: TextUnit = 16.sp,
    paddingVertical: Dp = 8.dp,
    height: Dp = 56.dp,
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, fontSize = fontSize) },
            placeholder = { Text(label, fontSize = fontSize) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = paddingVertical),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            isError = isError,
            visualTransformation = if (isPassword) PasswordVisualTransformation()
            else VisualTransformation.None,
            textStyle = LocalTextStyle.current.copy(color = purple, fontSize = fontSize)
        )
        if (showError && isError) {
            Text(
                text = "Campo requerido",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

