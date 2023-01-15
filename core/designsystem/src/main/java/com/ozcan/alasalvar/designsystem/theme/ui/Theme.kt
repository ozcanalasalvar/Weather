package com.ozcan.alasalvar.designsystem.theme.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = BlueDark,
    secondary = Color.White,
    secondaryVariant = LightTextSecondary,
    background = BlueDark,
    surface = BlueDarkSecond,
    error = Color.Red,
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    secondary = BlueDark,
    secondaryVariant = LightTextSecondary,
    background = BlueLightSecond,
    surface = Color.White,
    error = Color.Red,
)

@Composable
fun WeatherTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}