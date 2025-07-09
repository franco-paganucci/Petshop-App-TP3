package com.example.petshopapptp3.screens.onBoardign

import androidx.compose.foundation.Image
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.onBoarding_Title),
                fontSize = sizes.titleFontSize,
                lineHeight = sizes.titleLineHeight,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = sizes.paddingTop)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.illustration),
                contentDescription = "Illustration",
                modifier = Modifier
                    .size(sizes.imageSize)
                    .padding(top = sizes.paddingTop)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.onBoarding_Subtitle),
                fontSize = sizes.subtitleFontSize,
                lineHeight = sizes.subtitleLineHeight,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = sizes.paddingTop)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "● ○ ○",
                fontSize = 18.sp,
                color = purple,
                modifier = Modifier.padding(top = sizes.paddingTop)
            )

            StartButton(
                onClick = { navController.navigate(Screen.Login.route) },
                text = "Get Started",
                ButtonColor = purple
                )
        }
    }
}