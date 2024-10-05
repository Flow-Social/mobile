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
	val subscribers: ProfileSubscribers? = null,
) {
	fun toUiState(): ProfileScreenState {
		if (isLoading) return ProfileScreenState.Loading

		if (isError) return ProfileScreenState.Error

		if (shortUsername != null && avatarUrl != null && description != null && displayName != null && subscribers != null) {
			return ProfileScreenState.Success(
				shortUsername = shortUsername,
				avatarUri = avatarUrl,
				description = description,
				displayName = displayName,
				subscribers = subscribers,
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
			delay(700L)

			_state.update {
				it.copy(
					shortUsername = "demndevel",
					avatarUrl = Uri.parse("https://avatars.githubusercontent.com/u/69032700?v=4"),
					displayName = "Demn Demov",
					description = "I like meowing, coding and hugs. Welcome to my zone!!",
					isLoading = false,
					subscribers = ProfileSubscribers(
						firstAvatar = Uri.parse("https://avatars.githubusercontent.com/u/46930374?v=4"),
						secondAvatar = Uri.parse("https://avatars.githubusercontent.com/u/69369034?v=4"),
						subscribersCount = 123456
					)
				)
			}
		}
	}
}