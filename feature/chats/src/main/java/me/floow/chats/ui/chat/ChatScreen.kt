package me.floow.chats.ui.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.floow.chats.ui.chat.components.ChatScreenTopBar
import me.floow.chats.ui.chat.components.CurrentReply
import me.floow.chats.ui.chat.components.MessageInputField
import me.floow.chats.ui.chat.states.ErrorState
import me.floow.chats.ui.chat.states.HasDataState
import me.floow.chats.ui.chat.states.LoadingState
import me.floow.chats.ui.chat.states.NoMessagesState
import me.floow.chats.uilogic.chat.ChatMessage
import me.floow.chats.uilogic.chat.ChatScreenUiState
import me.floow.uikit.R
import me.floow.uikit.theme.ElevanagonShape

@Composable
fun ChatScreen(
	onProfileClick: () -> Unit,
	onTopBarDropdownClick: () -> Unit,
	onChatBubbleClick: (ChatMessage) -> Unit,
	onReply: (ChatMessage) -> Unit,
	onReplyClick: (ChatMessage) -> Unit,
	onCurrentReplyClose: () -> Unit,
	onCurrentReplyClick: () -> Unit,
	onMessageInputFieldValueChange: (String) -> Unit,
	onEmojiPickerClick: () -> Unit,
	onSendClick: () -> Unit,
	state: ChatScreenUiState,
	modifier: Modifier = Modifier
) {
	Scaffold(
		topBar = {
			ChatScreenTopBar(
				profileName = state.chatInterlocutorName,
				isOnline = false,
				profileAvatar = { modifier ->
					Image(
						painterResource(R.drawable.cute_girl),
						null,
						modifier
							.clip(ElevanagonShape),
					)
				},
				onDropdownClick = onTopBarDropdownClick,
				onProfileClick = onProfileClick,
				modifier = Modifier
			)
		},
		contentWindowInsets = WindowInsets(0.dp),
		modifier = modifier
	) { innerPadding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.imePadding()
		) {
			val commonModifier = Modifier
				.padding(innerPadding)
				.fillMaxWidth()
				.weight(1f)
				.background(MaterialTheme.colorScheme.surfaceContainer)

			when (state) {
				is ChatScreenUiState.Loading -> {
					LoadingState(commonModifier)
				}

				is ChatScreenUiState.Error -> {
					ErrorState(commonModifier)
				}

				is ChatScreenUiState.NoMessages -> {
					NoMessagesState(commonModifier)
				}

				is ChatScreenUiState.HasData -> {
					HasDataState(
						state = state,
						onChatBubbleClick = onChatBubbleClick,
						onReply = onReply,
						onReplyClick = onReplyClick,
						modifier = commonModifier
					)
				}
			}

			state.messageFieldReply?.let { reply ->
				CurrentReply(
					userNameToReply = reply.replyAuthorName,
					replyMessageText = reply.replyMessageText,
					onClose = onCurrentReplyClose,
					onClick = onCurrentReplyClick,
					modifier = Modifier
						.fillMaxWidth()
				)
			}

			HorizontalDivider()

			MessageInputField(
				value = state.messageFieldValue,
				onValueChange = onMessageInputFieldValueChange,
				onEmojiPickerClick = onEmojiPickerClick,
				onSendClick = onSendClick,
				sendButtonActive = state.messageFieldValue.isNotEmpty(),
				modifier = Modifier
					.fillMaxWidth()
			)
		}
	}
}
