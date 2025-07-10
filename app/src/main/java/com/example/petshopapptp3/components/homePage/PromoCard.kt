package com.example.petshopapptp3.components.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex

import com.example.petshopapptp3.R
import com.example.petshopapptp3.util.responsiveSizes

@Composable
fun PromoCard() {
    val sizes = responsiveSizes()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(sizes.promoCardHeight)
            .graphicsLayer {
                clip = false
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.promo_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
        )
        Image(
            painter = painterResource(id = R.drawable.promo_product),
            contentDescription = null,
            modifier = Modifier
                .size(sizes.promoProductSize)
                .align(Alignment.CenterStart)
                .offset(x = (-16).dp)
                .zIndex(1f)
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 24.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Royal Canin\nAdult Pomeraniann",
                fontWeight = FontWeight.Bold,
                fontSize = sizes.promoTitleFontSize,
                lineHeight = sizes.promoLineHeightTitle,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Get an interesting promo\nhere, without conditions",
                fontSize = sizes.promoSubtitleFontSize,
                lineHeight = sizes.promoLineHeightSubtitle,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}


