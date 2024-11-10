package me.floow.chats.uilogic.chats

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

private data class ChatsScreenVmState(
	val isLoading: Boolean = false,
	val isError: Boolean = false,
	val chats: List<Chat>? = null
) {
	fun toUiState(): ChatsScreenUiState {
		if (isLoading) return ChatsScreenUiState.Loading

		return if (chats != null && !isError) {
			ChatsScreenUiState.HasData(chats)
		} else {
			ChatsScreenUiState.Error
		}
	}
}

class ChatsScreenViewModel : ViewModel() {
	private val _state = MutableStateFlow(ChatsScreenVmState())

	val state: StateFlow<ChatsScreenUiState> = _state
		.map(ChatsScreenVmState::toUiState)
		.stateIn(viewModelScope, SharingStarted.Eagerly, ChatsScreenUiState.Loading)

	fun load() {
		viewModelScope.launch {
			_state.update {
				it.copy(
					isLoading = true,
					isError = false,
				)
			}

			delay(600L)


			_state.update {
				it.copy(
					isLoading = false,
					isError = false,
					chats = generateRandomChats(50)
				)
			}
		}
	}
}