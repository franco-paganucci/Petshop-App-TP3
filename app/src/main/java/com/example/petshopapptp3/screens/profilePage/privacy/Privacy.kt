package com.example.petshopapptp3.screens.profilePage.privacy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshopapptp3.components.shared.ArrowTitle

@Composable
fun PrivacyView(

) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ArrowTitle("hola")
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Terms of Use",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum sapien sodales mi sagittis hendrerit. Curabitur ut lectus nec orci cursus rhoncus. Donec a ultrices risus. Mauris ut erat ut urna rhoncus facilisis a eu neque. Ut iaculis viverra laoreet. In interdum, augue non auctor pharetra, felis ante gravida ante, quis mattis quam eros non quam. Vivamus scelerisque ante nec dapibus convallis. Vestibulum quis scelerisque leo. Vestibulum quis porttitor tellus, non finibus nibh. Quisque ut tempor nulla, sed consectetur tortor. Mauris volutpat viverra arcu non laoreet. Duis eu arcu nunc. Pellentesque ultricies facilisis faucibus. Duis magna sem, ultricies sed scelerisque efficitur, hendrerit at arcu.",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = "PetApp Service",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 48.dp)
            )

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum sapien sodales mi sagittis hendrerit. Curabitur ut lectus nec orci cursus rhoncus. Donec a ultrices risus. Mauris ut erat ut urna rhoncus facilisis a eu neque. Ut iaculis viverra laoreet. In interdum, augue non auctor pharetra, felis ante gravida ante, quis mattis quam eros non quam. Vivamus scelerisque ante nec dapibus convallis. Vestibulum quis scelerisque leo. Vestibulum quis porttitor tellus, non finibus nibh. Quisque ut tempor nulla, sed consectetur tortor. Mauris volutpat viverra arcu non laoreet. Duis eu arcu nunc. Pellentesque ultricies facilisis faucibus. Duis magna sem, ultricies sed scelerisque efficitur, hendrerit at arcu.",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 24.dp)
            )
        }
    }

@Preview(showBackground = true)
@Composable
fun ShowPrivacy(){
    PrivacyView()
}