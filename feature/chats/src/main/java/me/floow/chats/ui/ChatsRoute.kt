package me.floow.chats.ui

import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.floow.uikit.util.SetNavigationBarColor

@Composable
fun ChatsRoute(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ChatsScreen(
        onSearchClick = onSearchClick,
        modifier = modifier
    )

    SetNavigationBarColor(
        NavigationBarDefaults.containerColor
    )
}