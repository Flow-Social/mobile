package me.floow.chats

import android.widget.Toast
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import me.floow.chats.ui.chats.ChatsScreen
import me.floow.chats.uilogic.chats.ChatsScreenViewModel
import me.floow.uikit.util.SetNavigationBarColor

@Composable
fun ChatsRoute(
	onSearchClick: () -> Unit,
	vm: ChatsScreenViewModel,
	modifier: Modifier = Modifier
) {
	val state by vm.state.collectAsState()
	val context = LocalContext.current

	LaunchedEffect(Unit) {
		vm.load()
	}

	ChatsScreen(
		onSearchClick = onSearchClick,
		state = state,
		onChatClick = {
			// TODO
			Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
		},
		modifier = modifier
	)

	SetNavigationBarColor(
		NavigationBarDefaults.containerColor
	)
}