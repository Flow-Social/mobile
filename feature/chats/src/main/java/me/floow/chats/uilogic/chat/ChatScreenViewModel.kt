package me.floow.chats.uilogic.chat

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ChatScreenVmState(
	val messageFieldValue: String = "",
	val chatInterlocutorId: Long = -1L,
	val chatInterlocutorName: String = "",
	val chatInterlocutorAvatarUrl: Uri? = null,
	val isLoading: Boolean = false,
	val isError: Boolean = false,
	val messages: List<DatedChatMessages>? = null,
	val messageFieldReply: MessageFieldReply? = null
) {
	fun toUiState(): ChatScreenUiState {
		return when {
			isLoading -> {
				ChatScreenUiState.Loading(
					chatInterlocutorId = chatInterlocutorId,
					chatInterlocutorName = chatInterlocutorName,
					chatInterlocutorAvatarUrl = chatInterlocutorAvatarUrl,
					messageFieldValue = messageFieldValue,
					messageFieldReply = messageFieldReply,
				)
			}

			isError || messages == null -> {
				ChatScreenUiState.Error(
					chatInterlocutorId = chatInterlocutorId,
					chatInterlocutorName = chatInterlocutorName,
					chatInterlocutorAvatarUrl = chatInterlocutorAvatarUrl,
					messageFieldValue = messageFieldValue,
					messageFieldReply = messageFieldReply,
				)
			}

			messages.isEmpty() -> {
				ChatScreenUiState.NoMessages(
					chatInterlocutorId = chatInterlocutorId,
					chatInterlocutorName = chatInterlocutorName,
					chatInterlocutorAvatarUrl = chatInterlocutorAvatarUrl,
					messageFieldValue = messageFieldValue,
					messageFieldReply = messageFieldReply,
				)
			}

			else -> {
				ChatScreenUiState.HasData(
					chatInterlocutorId = chatInterlocutorId,
					chatInterlocutorName = chatInterlocutorName,
					chatInterlocutorAvatarUrl = chatInterlocutorAvatarUrl,
					messageFieldValue = messageFieldValue,
					messages = messages,
					messageFieldReply = messageFieldReply,
				)
			}
		}
	}
}

class ChatScreenViewModel() : ViewModel() {
	private val _state: MutableStateFlow<ChatScreenVmState> = MutableStateFlow(
		ChatScreenVmState()
	)

	val state: StateFlow<ChatScreenUiState> = _state
		.map(ChatScreenVmState::toUiState)
		.stateIn(
			viewModelScope,
			SharingStarted.Eagerly,
			ChatScreenUiState.Loading(
				chatInterlocutorId = 0L,
				chatInterlocutorAvatarUrl = null,
				messageFieldValue = "",
				chatInterlocutorName = "null",
				messageFieldReply = null
			)
		)

	fun setInitialData(
		chatInterlocutorId: Long,
		chatInterlocutorName: String,
		chatInterlocutorAvatarUrl: Uri?
	) {
		_state.update {
			it.copy(
				chatInterlocutorId = chatInterlocutorId,
				chatInterlocutorName = chatInterlocutorName,
				chatInterlocutorAvatarUrl = chatInterlocutorAvatarUrl
			)
		}
	}

	fun loadData() {
		viewModelScope.launch {
			_state.update {
				it.copy(
					isLoading = true
				)
			}

			delay(300L)

			_state.update {
				it.copy(
					isLoading = false,
					messages = generateChatMessages()
						.sortedBy { it.dateTime }
						.groupBy { it.dateTime.toLocalDate() }
						.map { (datetime, messages) ->
							DatedChatMessages(
								datetime = datetime,
								messages = messages
							)
						}
				)
			}
		}
	}

	fun closeCurrentReply() {
		_state.update {
			it.copy(
				messageFieldReply = null
			)
		}
	}

	fun updateMessageInputField(newValue: String) {
		_state.update {
			it.copy(
				messageFieldValue = newValue
			)
		}
	}

	fun sendMessage() {
		TODO("Not yet implemented")
	}

	fun addCurrentReply(chatMessage: ChatMessage) {
		val replyAuthorName = when (chatMessage) {
			is PrimaryInMessage, is ReplyInMessage -> {
				_state.value.chatInterlocutorName
			}

			else -> {
				"You" // TODO
			}
		}

		_state.update {
			it.copy(
				messageFieldReply = MessageFieldReply(
					replyId = chatMessage.id,
					replyAuthorName = replyAuthorName,
					replyMessageText = chatMessage.messageText
				)
			)
		}
	}
}