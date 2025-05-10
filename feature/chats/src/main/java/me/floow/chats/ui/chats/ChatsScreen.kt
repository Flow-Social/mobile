package me.floow.chats.ui.chats

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.chats.ui.chats.states.HasDataState
import me.floow.chats.uilogic.chats.Chat
import me.floow.chats.uilogic.chats.ChatsScreenUiState
import me.floow.uikit.R
import me.floow.uikit.components.topbar.TitleTopBarWithActionButton

@Composable
internal fun ChatsScreen(
	onSearchClick: () -> Unit,
	state: ChatsScreenUiState,
	onChatClick: (Chat) -> Unit,
	modifier: Modifier = Modifier
) {
	Scaffold(
		topBar = {
			TitleTopBarWithActionButton(
				titleText = "Чаты",
				onActionButtonClick = onSearchClick,
				icon = {
					Icon(
						painterResource(R.drawable.search_icon),
						null
					)
				}
			)
		},
		contentWindowInsets = WindowInsets(0.dp),
		modifier = modifier
	) { innerPadding ->
		val commonModifier = Modifier
			.fillMaxSize()
			.padding(innerPadding)

		when (state) {
			is ChatsScreenUiState.Loading -> {
				Box(commonModifier, Alignment.Center) {
					CircularProgressIndicator()
				}
			}

			is ChatsScreenUiState.Error -> {
				Box(commonModifier, Alignment.Center) {
					Text(text = "error")
				}
			}

			is ChatsScreenUiState.NoChats -> {
				Box(commonModifier, Alignment.Center) {
					Text(text = "no chats sorry")
				}
			}

			is ChatsScreenUiState.HasData -> {
				HasDataState(
					state = state,
					onChatClick = onChatClick,
					modifier = commonModifier
				)
			}
		}
	}
}

@Preview
@Composable
private fun ChatsScreenPreview_Loading() {
	ChatsScreen(
		onSearchClick = {},
		state = ChatsScreenUiState.Loading,
		onChatClick = {},
		modifier = Modifier.fillMaxSize()
	)
}

@Preview
@Composable
private fun ChatsScreenPreview_Error() {
	ChatsScreen(
		onSearchClick = {},
		state = ChatsScreenUiState.Error,
		onChatClick = {},
		modifier = Modifier.fillMaxSize()
	)
}

@Preview
@Composable
private fun ChatsScreenPreview_HasData() {
	ChatsScreen(
		onSearchClick = {},
		state = ChatsScreenUiState.Loading,
		onChatClick = {},
		modifier = Modifier.fillMaxSize()
	)
}