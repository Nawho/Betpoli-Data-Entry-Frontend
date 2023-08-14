package com.example.helloworld.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val PeriodistasDarkColorScheme = darkColorScheme(
    primary = Color(0xFF0EB0C7),
    secondary = Color(0xFF2BFAA0),
    onSurface = Color(0xFFFFFFFF), //text
    background = Color(0xFF222222),
    primaryContainer = Color(0xFF4A4A4A),
    secondaryContainer = Color(0xFF2C2C2C)
)

private val PeriodistasLightColorScheme = lightColorScheme(
    primary = Color(0xFF0EB0C7),
    secondary = Color(0xFF2BFAA0),
    onSurface = Color(0xFF4E4B4B), //text
    background = Color(0xFFE2E2E2),
    primaryContainer = Color(0xFFD8D8D8), //card bg
    secondaryContainer = Color(0xFFC6C6C6) //inputs bg
)

/*
private val ApuestasDarkColorScheme = darkColorScheme(
    primary = Color(0xFF2BFAA0),
    secondary = Color(0xFF0EB0C7),
    onSurface = Color(0xFFFFFFFF), //text
    background = Color(0xFF222222),
    primaryContainer = Color(0xFF4A4A4A),
    secondaryContainer = Color(0xFF2C2C2C)
)*/


@Composable
fun PeriodistasTheme (
        darkTheme: Boolean = isSystemInDarkTheme(), //isSystemInDarkTheme(),
        dynamicColor: Boolean = false,
        content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> PeriodistasDarkColorScheme
        else -> PeriodistasLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
    )
}