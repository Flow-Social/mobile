package me.floow.chats.ui.chats.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.floow.chats.uilogic.chats.Chat

@Composable
internal fun ChatsList(
	chats: List<Chat>,
	onChatClick: (Chat) -> Unit,
	modifier: Modifier = Modifier
) {
	LazyColumn(
		modifier = modifier,
	) {
		item {
			Spacer(Modifier.height(8.dp))
		}

		items(chats) { chat ->
			ChatListItem(
				chat = chat,
				onClick = onChatClick,
				modifier = Modifier
					.fillMaxWidth()
			)
		}
	}
}