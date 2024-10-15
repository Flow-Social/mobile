package me.floow.login.uilogic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class CreateProfileVmState(
	val name: ValidatedField = ValidatedField.Valid(""),
	val username: ValidatedField = ValidatedField.Valid(""),
	val bio: ValidatedField = ValidatedField.Valid(""),
	val isUploading: Boolean = false
) {
	fun toUiState(): CreateProfileState {
		return if (isUploading) {
			CreateProfileState.Uploading
		} else {
			CreateProfileState.Edit(
				name = name,
				username = username,
				bio = bio
			)
		}
	}
}

class CreateProfileViewModel : ViewModel() {
	private val _state = MutableStateFlow(CreateProfileVmState())

	val state: StateFlow<CreateProfileState> = _state
		.map(CreateProfileVmState::toUiState)
		.stateIn(
			viewModelScope,
			SharingStarted.Eagerly,
			CreateProfileState.Edit()
		)

	fun updateName(newValue: String) {
		val field: ValidatedField = if (newValue.isEmpty()) {
			ValidatedField.Invalid(
				value = newValue,
				errorType = ValidationErrorType.ShouldNotBeEmpty
			)
		} else {
			ValidatedField.Valid(
				value = newValue
			)
		}

		_state.update {
			it.copy(
				name = field
			)
		}
	}

	fun updateUsername(newValue: String) {
		val field: ValidatedField = if (newValue.isEmpty()) {
			ValidatedField.Invalid(
				value = newValue,
				errorType = ValidationErrorType.ShouldNotBeEmpty
			)
		} else {
			ValidatedField.Valid(
				value = newValue
			)
		}

		_state.update {
			it.copy(
				username = field
			)
		}
	}

	fun updateBio(newValue: String) {
		val field: ValidatedField = if (newValue.isEmpty()) {
			ValidatedField.Invalid(
				value = newValue,
				errorType = ValidationErrorType.ShouldNotBeEmpty
			)
		} else {
			ValidatedField.Valid(
				value = newValue
			)
		}

		_state.update {
			it.copy(
				bio = field
			)
		}
	}
}