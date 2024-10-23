package me.floow.profile.uilogic.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.floow.domain.api.models.EditProfileData
import me.floow.domain.data.GetDataResponse
import me.floow.domain.data.UpdateDataResponse
import me.floow.domain.data.repos.ProfileRepository

data class CreateProfileVmState(
	val name: ValidatedField = ValidatedField.Valid(""),
	val username: ValidatedField = ValidatedField.Valid(""),
	val bio: ValidatedField = ValidatedField.Valid(""),
	val isUploading: Boolean = false
) {
	fun toUiState(): EditProfileState {
		return if (isUploading) {
			EditProfileState.Uploading
		} else {
			EditProfileState.Edit(
				name = name,
				username = username,
				bio = bio
			)
		}
	}
}

class EditProfileViewModel(
	private val _profileRepository: ProfileRepository
) : ViewModel() {
	private val _state = MutableStateFlow(CreateProfileVmState())

	val state: StateFlow<EditProfileState> = _state
		.map(CreateProfileVmState::toUiState)
		.stateIn(viewModelScope, SharingStarted.Eagerly, EditProfileState.Edit())

	fun loadData() {
		viewModelScope.launch {
			when (val result = _profileRepository.getSelfData()) {
				is GetDataResponse.Success -> _state.update {
					it.copy(
						name = ValidatedField.Valid(
							value = result.data.name ?: ""
						),
						bio = ValidatedField.Valid(
							value = result.data.biography ?: ""
						),
					)
				}
			}
		}
	}

	fun updateName(newValue: String) {
		val field: ValidatedField = if (newValue.isEmpty()) {
			ValidatedField.Invalid(
				value = newValue,
				errorType = ValidationErrorType.ShouldNotBeEmpty
			)
		} else if (newValue.length > 32) {
			ValidatedField.Invalid(
				value = newValue,
				errorType = ValidationErrorType.TextTooLong
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
		} else if (newValue.length > 32) {
			ValidatedField.Invalid(
				value = newValue,
				errorType = ValidationErrorType.TextTooLong
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

	fun updateBiography(newValue: String) {
		val field: ValidatedField = if (newValue.isEmpty()) {
			ValidatedField.Invalid(
				value = newValue,
				errorType = ValidationErrorType.ShouldNotBeEmpty
			)
		} else if (newValue.length > 140) {
			ValidatedField.Invalid(
				value = newValue,
				errorType = ValidationErrorType.TextTooLong
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

	fun updateProfile(onSuccess: () -> Unit, onFailure: () -> Unit) {
		viewModelScope.launch {
			validateAll()

			val allValid = _state.value.bio is ValidatedField.Valid &&
					_state.value.name is ValidatedField.Valid &&
					_state.value.username is ValidatedField.Valid

			if (!allValid) {
				onFailure()
				return@launch
			}

			_state.update {
				it.copy(
					isUploading = true
				)
			}

			val result = _profileRepository.edit(
				data = EditProfileData(
					name = _state.value.name.value,
					bio = _state.value.bio.value
				)
			)

			if (result is UpdateDataResponse.Success) {
				onSuccess()
			} else {
				onFailure()
			}

			_state.update {
				it.copy(
					isUploading = false
				)
			}
		}
	}

	private fun validateAll() {
		updateName(_state.value.name.value)
		updateUsername(_state.value.username.value)
		updateBiography(_state.value.bio.value)
	}
}