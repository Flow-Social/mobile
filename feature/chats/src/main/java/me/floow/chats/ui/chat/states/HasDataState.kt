package me.floow.chats.ui.chat.states

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.floow.chats.ui.chat.components.DateSeparator
import me.floow.chats.ui.chat.components.replyable.ReplyableChatBubble
import me.floow.chats.uilogic.chat.ChatMessage
import me.floow.chats.uilogic.chat.ChatScreenUiState
import me.floow.chats.uilogic.chat.PrimaryOutMessage
import me.floow.chats.uilogic.chat.ReplyOutMessage
import me.flowme.chats.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HasDataState(
	state: ChatScreenUiState.HasData,
	onChatBubbleClick: (ChatMessage) -> Unit,
	onReply: (ChatMessage) -> Unit,
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
			state.messages.forEach { (date, messageList) ->
				stickyHeader {
					Spacer(Modifier.height(8.dp))

					Row(
						horizontalArrangement = Arrangement.Center,
						modifier = Modifier.fillMaxWidth(),
					) {
						DateSeparator(
							text = getDateSeparatorText(date)
						)
					}

					Spacer(Modifier.height(8.dp))
				}

				items(messageList) { message ->
					val isOut: Boolean = message is PrimaryOutMessage || message is ReplyOutMessage

					Row(
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 14.dp),
						horizontalArrangement = if (isOut) Arrangement.End else Arrangement.Start
					) {
						ReplyableChatBubble(
							chatMessage = message,
							onClick = onChatBubbleClick,
							onReplyClick = onReplyClick,
							onReply = onReply,
							modifier = Modifier
								.fillMaxWidth()
						)
//						ChatBubble(
//							chatMessage = message,
//							onClick = onChatBubbleClick,
//							onReplyClick = onReplyClick,
//							modifier = Modifier
//						)
					}

					Spacer(Modifier.height(8.dp))
				}
			}
		}
	}
}

@Composable
fun getDateSeparatorText(date: LocalDate): String {
	return if (date == LocalDate.now()) {
		stringResource(R.string.today)
	} else {
		date.format(DateTimeFormatter.ofPattern("dd.MM.yy"))
	}
}
