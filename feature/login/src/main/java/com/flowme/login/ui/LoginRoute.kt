package com.flowme.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginRoute(
    onGoToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    LoginScreen(onGoToHome, modifier)
}