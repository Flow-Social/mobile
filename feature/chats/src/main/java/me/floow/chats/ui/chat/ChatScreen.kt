package me.floow.chats.ui.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.floow.chats.ui.chat.components.ChatScreenTopBar
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
	onReplyClick: (ChatMessage) -> Unit,
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
		val commonModifier = Modifier
			.padding(innerPadding)
			.fillMaxSize()

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
					onReplyClick = onReplyClick,
					modifier = commonModifier
				)
			}
		}
	}
}
