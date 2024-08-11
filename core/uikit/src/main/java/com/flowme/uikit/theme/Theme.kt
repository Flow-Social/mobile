package com.flowme.uikit.theme

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
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
        fontWeight = FontWeight.Medium,
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
)

val LocalTypography = staticCompositionLocalOf {
    FlowTypography()
}

val LocalColorScheme = staticCompositionLocalOf {
    FlowColorScheme()
}

object FlowCustomTheme {
    val typography: FlowTypography
        @Composable
        get() = LocalTypography.current
    val colorScheme: FlowColorScheme
        @Composable
        get() = LocalColorScheme.current
}

private val DarkFlowColorScheme = FlowColorScheme(
    statusBarColor = Color.Black,
)

private val LightFlowColorScheme = FlowColorScheme(
    statusBarColor = Color.White,
)

private val DarkColorScheme = darkColorScheme()

private val LightColorScheme = lightColorScheme(
    primary = ChetwodeBlue800,
    onPrimary = ChetwodeBlue50,
    secondary = MoodyBlue700,
    onSecondary = Color(0xFFF0F2FD),
    secondaryContainer = ChetwodeBlue200,
    onSecondaryContainer = ChetwodeBlue900,
    surface = Color.White,
    background = Color.White,
    onBackground = Color(0xFF1D1B20),
    onSurface = Color(0xFF1D1B20),
    surfaceContainerLowest = ChetwodeBlue50,
    inversePrimary = MoodyBlue400,
    scrim = Color.Black,
)

@Composable
fun FlowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = generateColorScheme(dynamicColor, darkTheme, context)

    val flowColorScheme = if (darkTheme) DarkFlowColorScheme else LightFlowColorScheme
    val typography = FlowTypography()

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }


    MaterialTheme(
        content = {
            CompositionLocalProvider(
                LocalTypography provides typography,
                LocalColorScheme provides flowColorScheme,
                LocalRippleTheme provides FlowRippleTheme
            ) {
                content()
            }
        },
        colorScheme = colorScheme
    )
}

@Composable
private fun generateColorScheme(
    dynamicColor: Boolean,
    darkTheme: Boolean,
    context: Context
) = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> DarkColorScheme

    else -> LightColorScheme
}.let(::overrideColors)

private fun overrideColors(colorScheme: ColorScheme): ColorScheme =
    colorScheme.copy(outline = colorScheme.outline.copy(alpha = 0.5f))