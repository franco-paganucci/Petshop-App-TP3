package com.example.petshopapptp3.components.loginComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

import com.example.petshopapptp3.R
import com.example.petshopapptp3.util.LocalWindowSize
import com.example.petshopapptp3.util.WindowSize
import com.example.petshopapptp3.util.responsiveSizes

@Composable
fun SocialButtons() {
    val sizes = responsiveSizes()
    val isCompact = LocalWindowSize.current == WindowSize.SMALL

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isCompact) Arrangement.SpaceEvenly else Arrangement.spacedBy(12.dp)
    ) {
        SocialButton(
            text = if (isCompact) "" else "Google",
            icon = painterResource(id = R.drawable.google_icon),
            height = sizes.socialButtonHeight,
            iconSize = sizes.socialButtonIconSize,
            paddingHorizontal = sizes.socialButtonPaddingHorizontal
        )
        SocialButton(
            text = if (isCompact) "" else "Facebook",
            icon = painterResource(id = R.drawable.facebook_icon),
            height = sizes.socialButtonHeight,
            iconSize = sizes.socialButtonIconSize,
            paddingHorizontal = sizes.socialButtonPaddingHorizontal
        )
    }
}


@Composable
fun SocialButton(
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    height: Dp = 56.dp,
    iconSize: Dp = 20.dp,
    paddingHorizontal: Dp = 8.dp
) {
    OutlinedButton(
        onClick = {},
        modifier = modifier
            .height(height)
            .padding(horizontal = paddingHorizontal),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(iconSize),
            tint = Color.Unspecified
        )
        if (text.isNotEmpty()) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(text)
        }
    }
}

