package com.example.petshopapptp3.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ResponsiveSizes(
    val titleFontSize: TextUnit,
    val titleLineHeight: TextUnit,
    val subtitleFontSize: TextUnit,
    val subtitleLineHeight: TextUnit,
    val imageSize: Dp,
    val paddingTop: Dp,

    // Layout
    val paddingHorizontal: Dp,
    val spacerHeightLarge: Dp,
    val subtitleFont: TextUnit,
    val inputPaddingVertical: Dp,

    // Inputs & Buttons
    val inputHeight: Dp,
    val inputFontSize: TextUnit,
    val buttonHeight: Dp,
    val buttonFontSize: TextUnit,
    val socialButtonHeight: Dp,
    val socialButtonIconSize: Dp,
    val socialButtonPaddingHorizontal: Dp,

    val promoCardHeight: Dp,
    val promoProductSize: Dp,
    val promoProductOffsetX: Dp,
    val promoProductOffsetY: Dp,
    val promoTitleFontSize: TextUnit,
    val promoSubtitleFontSize: TextUnit,
    val promoLineHeightTitle: TextUnit,
    val promoLineHeightSubtitle: TextUnit,

    val loginBottomSpacing: Dp
)

@Composable
fun responsiveSizes(): ResponsiveSizes {
    return when (LocalWindowSize.current) {
        WindowSize.SMALL -> ResponsiveSizes(
            titleFontSize = 16.sp,
            titleLineHeight = 16.sp * 1.3f,
            subtitleFontSize = 12.sp,
            subtitleLineHeight = 12.sp * 1.4f,
            imageSize = 150.dp,
            paddingTop = 4.dp,
            paddingHorizontal = 16.dp,
            spacerHeightLarge = 16.dp,
            subtitleFont = 12.sp,
            inputPaddingVertical = 4.dp,
            inputHeight = 40.dp,
            inputFontSize = 12.sp,
            buttonHeight = 40.dp,
            buttonFontSize = 12.sp,
            socialButtonHeight = 40.dp,
            socialButtonIconSize = 16.dp,
            socialButtonPaddingHorizontal = 4.dp,
            promoCardHeight = 120.dp,
            promoProductSize = 160.dp,
            promoProductOffsetX = (-36).dp,
            promoProductOffsetY = (-32).dp,
            promoTitleFontSize = 14.sp,
            promoSubtitleFontSize = 12.sp,
            promoLineHeightTitle = 16.sp,
            promoLineHeightSubtitle = 14.sp,
            loginBottomSpacing = 30.dp
            )

        WindowSize.MEDIUM -> ResponsiveSizes(
            titleFontSize = 20.sp,
            titleLineHeight = 24.sp * 1.3f,
            subtitleFontSize = 14.sp,
            subtitleLineHeight = 14.sp * 1.3f,
            imageSize = 260.dp,
            paddingTop = 8.dp,
            paddingHorizontal = 24.dp,
            spacerHeightLarge = 20.dp,
            subtitleFont = 14.sp,
            inputPaddingVertical = 8.dp,
            inputHeight = 56.dp,
            inputFontSize = 14.sp,
            buttonHeight = 56.dp,
            buttonFontSize = 16.sp,
            socialButtonHeight = 56.dp,
            socialButtonIconSize = 20.dp,
            socialButtonPaddingHorizontal = 8.dp,
            promoCardHeight = 140.dp,
            promoProductSize = 180.dp,
            promoProductOffsetX = 0.dp,
            promoProductOffsetY = 0.dp,
            promoTitleFontSize = 16.sp,
            promoSubtitleFontSize = 14.sp,
            promoLineHeightTitle = 18.sp,
            promoLineHeightSubtitle = 16.sp,
            loginBottomSpacing = 50.dp
            )

        WindowSize.LARGE -> ResponsiveSizes(
            titleFontSize = 36.sp,
            titleLineHeight = 36.sp * 1.3f,
            subtitleFontSize = 16.sp,
            subtitleLineHeight = 16.sp * 1.4f,
            imageSize = 360.dp,
            paddingTop = 24.dp,
            paddingHorizontal = 32.dp,
            spacerHeightLarge = 24.dp,
            subtitleFont = 16.sp,
            inputPaddingVertical = 12.dp,
            inputHeight = 64.dp,
            inputFontSize = 16.sp,
            buttonHeight = 64.dp,
            buttonFontSize = 20.sp,
            socialButtonHeight = 64.dp,
            socialButtonIconSize = 24.dp,
            socialButtonPaddingHorizontal = 12.dp,
            promoCardHeight = 180.dp,
            promoProductSize = 200.dp,
            promoProductOffsetX = (-48).dp,
            promoProductOffsetY = (-44).dp,
            promoTitleFontSize = 18.sp,
            promoSubtitleFontSize = 16.sp,
            promoLineHeightTitle = 20.sp,
            promoLineHeightSubtitle = 18.sp,
            loginBottomSpacing = 80.dp
            )
    }
}
