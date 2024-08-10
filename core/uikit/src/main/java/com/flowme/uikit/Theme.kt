package com.flowme.uikit

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

@Immutable
data class FlowTypography(
    val titleMedium: TextStyle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.38.sp
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.24).sp
    ),
    val labelMedium: TextStyle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.2.sp
    )
)

@Immutable
data class FlowColorScheme(
    val statusBarColor: Color = Color.White,
    val background: Color = Color.White,
)

val LocalTypography = staticCompositionLocalOf {
    FlowTypography()
}

val LocalColorscheme = staticCompositionLocalOf {
    FlowColorScheme()
}

object FlowTheme {
    val typography: FlowTypography
        @Composable
        get() = LocalTypography.current
    val colorScheme: FlowColorScheme
        @Composable
        get() = LocalColorscheme.current
}

private val DarkColorScheme = FlowColorScheme(
    statusBarColor = Color.Black,
)

private val LightColorScheme = FlowColorScheme(
    statusBarColor = Color.White,
)

@Composable
fun FlowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (darkTheme) DarkColorScheme
        else LightColorScheme

    val typography = FlowTypography()

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.statusBarColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalColorscheme provides colorScheme
    ) {
        MaterialTheme(content = content)
    }
}