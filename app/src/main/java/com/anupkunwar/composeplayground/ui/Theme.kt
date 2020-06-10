package com.anupkunwar.composeplayground.ui

import androidx.compose.Composable
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette
import com.anupkunwar.composeplayground.data.AppComponent

val DarkColorPalette = darkColorPalette(
    primary = Color(0xFFDD0D3C),
    primaryVariant = Color(0xFFC20029),
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFD00036),
    onError = Color.White
)

val LightColorPalette = lightColorPalette(
    primary = Color(0xFFDD0D3C),
    primaryVariant = Color(0xFFC20029),
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFD00036),
    onError = Color.White

)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    appComponent: AppComponent,
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}