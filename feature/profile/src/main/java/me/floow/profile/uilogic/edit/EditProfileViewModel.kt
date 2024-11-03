package me.floow.profile.uilogic.edit

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
import me.floow.domain.data.GetDataResponse
import me.floow.domain.data.UpdateDataResponse
import me.floow.domain.data.repos.ProfileRepository
import me.floow.domain.values.ProfileDescription
import me.floow.domain.values.ProfileName
import me.floow.domain.values.ProfileUsername
import me.floow.profile.ui.edit.EditProfileRouteInitialData
import me.floow.uikit.util.state.ValidatedField
import me.floow.uikit.util.state.ValidatedField.Companion.initialField
import me.floow.uikit.util.state.ValidationErrorType
import java.lang.IllegalStateException

data class CreateProfileVmState(
	val name: ValidatedField = initialField,
	val username: ValidatedField = initialField,
	val bio: ValidatedField = initialField,
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

	private val _hapticFeedbackFlow = MutableSharedFlow<Unit>()
	val hapticFeedbackFlow: SharedFlow<Unit> = _hapticFeedbackFlow

	fun loadData() {
		viewModelScope.launch {
			when (val result = _profileRepository.getSelfData()) {
				is GetDataResponse.Success -> _state.update {
					it.copy(
						name = ValidatedField.Valid(
							value = result.data.name?.value ?: ""
						),
						bio = ValidatedField.Valid(
							value = result.data.description?.value ?: ""
						),
						username = ValidatedField.Valid(
							value = result.data.description?.value ?: ""
						)
					)
				}
			}
		}
	}

	fun updateName(newValue: String) {
		try {
			ProfileName(newValue)

			_state.update {
				it.copy(
					name = ValidatedField.Valid(
						value = newValue,
					)
				)
			}
		} catch (ex: IllegalStateException) {
			_state.update {
				it.copy(
					name = ValidatedField.Invalid(
						value = it.name.value,
					)
				)
			}

			viewModelScope.launch {
				_hapticFeedbackFlow.emit(Unit)
			}
		}
	}

	fun updateUsername(newValue: String) {
		try {
			ProfileUsername(newValue)

			_state.update {
				it.copy(
					username = ValidatedField.Valid(
						value = newValue,
					)
				)
			}
		} catch (ex: IllegalStateException) {
			_state.update {
				it.copy(
					username = ValidatedField.Invalid(
						value = it.username.value,
					)
				)
			}

			viewModelScope.launch {
				_hapticFeedbackFlow.emit(Unit)
			}
		}
	}

	fun updateBiography(newValue: String) {
		try {
			ProfileDescription(newValue)

			_state.update {
				it.copy(
					bio = ValidatedField.Valid(
						value = newValue,
					)
				)
			}
		} catch (ex: IllegalStateException) {
			_state.update {
				it.copy(
					bio = ValidatedField.Invalid(
						value = it.bio.value,
					)
				)
			}

			viewModelScope.launch {
				_hapticFeedbackFlow.emit(Unit)
			}
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
					name = ProfileName(_state.value.name.value),
					username = ProfileUsername(_state.value.username.value),
					description = ProfileDescription(_state.value.bio.value)
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

	fun setInitialData(initialData: EditProfileRouteInitialData) {
		updateName(initialData.name)
		updateUsername(initialData.username)
		updateBiography(initialData.description)
	}
}