package me.floow.profile.uilogic

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class ProfileScreenVmState(
	val isLoading: Boolean = false,
	val isError: Boolean = false,
	val shortUsername: String? = null,
	val avatarUrl: Uri? = null,
	val displayName: String? = null,
	val description: String? = null,
) {
	fun toUiState(): ProfileScreenState {
		if (isLoading) return ProfileScreenState.Loading

		if (isError) return ProfileScreenState.Error

		if (shortUsername != null && avatarUrl != null && description != null && displayName != null) {
			return ProfileScreenState.Success(
				shortUsername = shortUsername,
				avatarUrl = avatarUrl,
				description = description,
				displayName = displayName,
			)
		} else {
			return ProfileScreenState.Error
		}
	}
}

class ProfileScreenViewModel(

) : ViewModel() {
	private val _state = MutableStateFlow(ProfileScreenVmState())

	val state = _state
		.map(ProfileScreenVmState::toUiState)
		.stateIn(viewModelScope, SharingStarted.Eagerly, ProfileScreenState.Loading)

	fun loadData() {
		_state.value = _state.value.copy(isLoading = true)

		viewModelScope.launch {
			delay(2280L)

			_state.update {
				it.copy(
					shortUsername = "demndevel",
					avatarUrl = Uri.EMPTY,
					displayName = "Demn Demov",
					description = "I like meowing, coding and hugs. Welcome to my zone!!",
					isLoading = true
				)
			}
		}
	}
}