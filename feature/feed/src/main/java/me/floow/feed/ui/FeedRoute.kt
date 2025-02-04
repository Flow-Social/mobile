package me.floow.feed.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.floow.uikit.util.SetNavigationBarColor

@Composable
fun FeedRoute(onPostCreateClick: () -> Unit, modifier: Modifier = Modifier) {
	FeedScreen(onPostCreateClick, modifier)

	SetNavigationBarColor(
		NavigationBarDefaults.containerColor
	)
}