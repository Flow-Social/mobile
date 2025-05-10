package me.floow.chats.uilogic.chats

interface ChatsScreenUiState {
	data object Loading : ChatsScreenUiState

	data object NoChats : ChatsScreenUiState

	data object Error : ChatsScreenUiState

	data class HasData(
		val chats: List<Chat>
	) : ChatsScreenUiState
}