package com.example.helloworld.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val warning: Color,
    val success: Color,
    val accent: Color,
    val card: Color,
    val container: Color
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        warning = Color.Unspecified,
        success = Color.Unspecified,
        accent = Color.Unspecified,
        card = Color.Unspecified,
        container = Color.Unspecified,
    )
}

@Composable
fun ExtendedTheme(
    /* ... */
    content: @Composable () -> Unit
) {
    val extendedColors = ExtendedColors(
        warning = Color.Unspecified,
        success = Color.Unspecified,
        accent = Color.Unspecified,
        card = Color.Unspecified,
        container = Color.Unspecified,
    )
    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            /* colors = ..., typography = ..., shapes = ... */
            content = content
        )
    }
}

// Use with eg. ExtendedTheme.colors.tertiary
object ExtendedTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}