package me.floow.chats

import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import me.floow.chats.ui.chats.ChatsScreen
import me.floow.chats.uilogic.chats.Chat
import me.floow.chats.uilogic.chats.ChatsScreenViewModel
import me.floow.uikit.util.SetNavigationBarColor

@Composable
fun ChatsRoute(
	onSearchClick: () -> Unit,
	onChatClick: (Chat) -> Unit,
	vm: ChatsScreenViewModel,
	modifier: Modifier = Modifier
) {
	val state by vm.state.collectAsState()

	LaunchedEffect(Unit) {
		vm.load()
	}

	ChatsScreen(
		onSearchClick = onSearchClick,
		state = state,
		onChatClick = onChatClick,
		modifier = modifier
	)

	SetNavigationBarColor(
		NavigationBarDefaults.containerColor
	)
}