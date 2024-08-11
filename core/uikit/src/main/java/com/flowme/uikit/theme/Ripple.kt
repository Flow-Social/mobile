package com.flowme.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.RippleTheme.Companion.defaultRippleAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

internal object FlowRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color {
        return MaterialTheme.colorScheme.primary
    }

    @Composable
    override fun rippleAlpha(): RippleAlpha = defaultRippleAlpha(
        MaterialTheme.colorScheme.primary,
        lightTheme = !isSystemInDarkTheme()
    )
}