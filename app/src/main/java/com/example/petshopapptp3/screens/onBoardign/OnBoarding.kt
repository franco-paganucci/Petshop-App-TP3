package com.example.petshopapptp3.screens.onBoardign

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.ui.theme.purple
import com.example.petshopapptp3.util.ProvideWindowSize
import com.example.petshopapptp3.util.responsiveSizes


@Composable
fun OnBoarding(navController: NavController) {
    ProvideWindowSize {
        val sizes = responsiveSizes()
        val scrollState = rememberScrollState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.onBoarding_Title),
                    fontSize = sizes.titleFontSize,
                    lineHeight = sizes.titleLineHeight,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Image(
                    painter = painterResource(id = R.drawable.illustration),
                    contentDescription = "Illustration",
                    modifier = Modifier.size(sizes.imageSize)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.onBoarding_Subtitle),
                    fontSize = sizes.subtitleFontSize,
                    lineHeight = sizes.subtitleLineHeight,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "● ○ ○",
                    fontSize = 18.sp,
                    color = purple
                )
            }

            StartButton(
                onClick = { navController.navigate(Screen.Login.route) },
                text = "Get Started",
                ButtonColor = purple,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
            )
        }
    }
}
