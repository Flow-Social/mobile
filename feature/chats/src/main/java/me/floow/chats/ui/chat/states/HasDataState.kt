package me.floow.chats.ui.chat.states

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.floow.chats.ui.chat.components.ChatBubble
import me.floow.chats.ui.chat.components.DateSeparator
import me.floow.chats.uilogic.chat.ChatMessage
import me.floow.chats.uilogic.chat.ChatScreenUiState
import me.floow.chats.uilogic.chat.PrimaryOutMessage
import me.floow.chats.uilogic.chat.ReplyOutMessage

@Composable
fun HasDataState(
	state: ChatScreenUiState.HasData,
	onChatBubbleClick: (ChatMessage) -> Unit,
	onReplyClick: (ChatMessage) -> Unit,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		LazyColumn(
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f)
		) {
			item {
				Spacer(Modifier.height(8.dp))

				Row(
					horizontalArrangement = Arrangement.Center,
					modifier = Modifier.fillMaxWidth(),
				) {
					DateSeparator(
						text = "Today" // todo
					)
				}

				Spacer(Modifier.height(8.dp))
			}

			items(state.messages) { message ->
				val isOut: Boolean = message is PrimaryOutMessage || message is ReplyOutMessage

				Row(
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 14.dp),
					horizontalArrangement = if (isOut) Arrangement.End else Arrangement.Start
				) {
					ChatBubble(
						chatMessage = message,
						onClick = onChatBubbleClick,
						onReplyClick = onReplyClick,
						modifier = Modifier
					)
				}

				Spacer(Modifier.height(8.dp))
			}
		}
	}
}