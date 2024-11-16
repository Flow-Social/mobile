package me.floow.chats

import android.net.Uri
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import me.floow.chats.ui.chat.ChatScreen
import me.floow.chats.uilogic.chat.ChatScreenViewModel
import me.floow.uikit.util.SetNavigationBarColor

data class ChatRouteInitialData(
	val chatInterlocutorId: Long,
	val chatInterlocutorName: String,
	val chatInterlocutorAvatarUrl: Uri?
)

@Composable
fun ChatRoute(
	initialData: ChatRouteInitialData,
	vm: ChatScreenViewModel,
	modifier: Modifier = Modifier
) {
	LaunchedEffect(Unit) {
		vm.setInitialData(
			initialData.chatInterlocutorId,
			initialData.chatInterlocutorName,
			initialData.chatInterlocutorAvatarUrl,
		)

		vm.loadData()
	}

	val state by vm.state.collectAsState()

	ChatScreen(
		onProfileClick = {},
		onTopBarDropdownClick = {},
		onReplyClick = {},
		onChatBubbleClick = {},
		state = state,
		modifier = modifier
	)

	SetNavigationBarColor(
		MaterialTheme.colorScheme.background
	)
}