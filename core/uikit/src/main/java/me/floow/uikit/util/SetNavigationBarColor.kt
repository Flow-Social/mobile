package me.floow.uikit.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

@Composable
fun SetNavigationBarColor(color: Color) {
    val view = LocalView.current
    LaunchedEffect(color) {
        val window = (view.context as Activity).window
        window.navigationBarColor = color.toArgb()
    }
}