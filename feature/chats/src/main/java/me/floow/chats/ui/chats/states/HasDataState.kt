package me.floow.chats.ui.chats.states

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.floow.chats.ui.chats.components.ChatsList
import me.floow.chats.uilogic.chats.Chat
import me.floow.chats.uilogic.chats.ChatsScreenUiState

@Composable
fun HasDataState(
	state: ChatsScreenUiState.HasData,
	onChatClick: (Chat) -> Unit,
	modifier: Modifier = Modifier
) {
	Column(modifier) {
		ChatsList(
			chats = state.chats,
			onChatClick = onChatClick,
			modifier = Modifier.fillMaxWidth()
		)
	}
}