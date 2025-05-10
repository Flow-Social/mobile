package me.floow.login.uilogic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.floow.domain.api.models.EditProfileData
import me.floow.domain.data.UpdateDataResponse
import me.floow.domain.data.repos.ProfileRepository
import me.floow.domain.values.ProfileDescription
import me.floow.domain.values.ProfileName
import me.floow.domain.values.ProfileUsername
import me.floow.domain.values.util.ValidationError
import me.floow.domain.values.util.ValueValidationResult
import me.floow.uikit.util.state.ValidatedField
import me.floow.uikit.util.state.ValidatedField.Companion.initialField

data class CreateProfileVmState(
	val name: ValidatedField = initialField,
	val username: ValidatedField = initialField,
	val bio: ValidatedField = initialField,
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

class CreateProfileViewModel(
	private val _profileRepository: ProfileRepository
) : ViewModel() {
	private val _state = MutableStateFlow(CreateProfileVmState())

	val state: StateFlow<CreateProfileState> = _state
		.map(CreateProfileVmState::toUiState)
		.stateIn(
			viewModelScope,
			SharingStarted.Eagerly,
			CreateProfileState.Edit()
		)

	private val _hapticFeedbackFlow = MutableSharedFlow<Unit>()
	val hapticFeedbackFlow: SharedFlow<Unit> = _hapticFeedbackFlow

	fun updateName(newValue: String) {
		val validationResult = ProfileName.createWithValidation(newValue)

		if (validationResult is ValueValidationResult.Invalid) {
			viewModelScope.launch {
				_hapticFeedbackFlow.emit(Unit)
			}

			if (validationResult.error == ValidationError.TooLarge) return

			_state.update {
				it.copy(
					name = ValidatedField.Invalid(
						value = newValue,
					)
				)
			}
		} else {
			_state.update {
				it.copy(
					name = ValidatedField.Valid(
						value = newValue,
					)
				)
			}
		}
	}

	fun updateUsername(newValue: String) {
		val validationResult = ProfileUsername.createWithValidation(newValue)

		if (validationResult is ValueValidationResult.Invalid) {
			viewModelScope.launch {
				_hapticFeedbackFlow.emit(Unit)
			}

			if (validationResult.error == ValidationError.TooLarge) return

			_state.update {
				it.copy(
					username = ValidatedField.Invalid(
						value = newValue,
					)
				)
			}
		} else {
			_state.update {
				it.copy(
					username = ValidatedField.Valid(
						value = newValue,
					)
				)
			}
		}
	}

	fun updateBio(newValue: String) {
		val validationResult = ProfileDescription.createWithValidation(newValue)

		if (validationResult is ValueValidationResult.Invalid) {
			viewModelScope.launch {
				_hapticFeedbackFlow.emit(Unit)
			}

			if (validationResult.error == ValidationError.TooLarge) return

			_state.update {
				it.copy(
					bio = ValidatedField.Invalid(
						value = newValue,
					)
				)
			}
		} else {
			_state.update {
				it.copy(
					bio = ValidatedField.Valid(
						value = newValue,
					)
				)
			}
		}
	}

	fun createProfile(onSuccess: () -> Unit, onFailure: () -> Unit) {
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
					name = ProfileName.create(_state.value.name.value),
					username = ProfileUsername.create(_state.value.username.value),
					description = ProfileDescription.create(_state.value.bio.value)
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
		updateBio(_state.value.bio.value)
	}
}