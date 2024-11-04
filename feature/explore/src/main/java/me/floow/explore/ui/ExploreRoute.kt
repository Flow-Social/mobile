package me.floow.explore.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.floow.uikit.util.SetNavigationBarColor

@Composable
fun ExploreRoute(
    modifier: Modifier = Modifier
) {
    ExploreScreen(modifier)

    SetNavigationBarColor(
        MaterialTheme.colorScheme.surfaceColorAtElevation(NavigationBarDefaults.Elevation)
    )
}