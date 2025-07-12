package com.example.petshopapptp3.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.CompositionLocalProvider

enum class WindowSize { SMALL, MEDIUM, LARGE }

val LocalWindowSize = compositionLocalOf { WindowSize.MEDIUM }

@Composable
fun ProvideWindowSize(content: @Composable () -> Unit) {
    BoxWithConstraints {
        val sizeClass = when {
            maxWidth < 400.dp -> WindowSize.SMALL       // Teléfonos compactos
            maxWidth < 600.dp -> WindowSize.MEDIUM      // Teléfonos regulares / grandes
            else -> WindowSize.LARGE                    // Tablets o pantallas grandes
        }


        CompositionLocalProvider(LocalWindowSize provides sizeClass) {
            content()
        }
    }
}