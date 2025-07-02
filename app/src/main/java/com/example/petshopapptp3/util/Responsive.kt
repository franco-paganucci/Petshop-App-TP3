package com.example.petshopapptp3.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.CompositionLocalProvider

enum class WindowSize { SMALL, MEDIUM, LARGE }

val LocalWindowSize = compositionLocalOf { WindowSize.MEDIUM }

@Composable
fun ProvideWindowSize(content: @Composable () -> Unit) {
    BoxWithConstraints {
        val sizeClass = when {
            maxWidth < 360.dp -> WindowSize.SMALL
            maxWidth < 600.dp -> WindowSize.MEDIUM
            else -> WindowSize.LARGE
        }

        CompositionLocalProvider(LocalWindowSize provides sizeClass) {
            content()
        }
    }
}