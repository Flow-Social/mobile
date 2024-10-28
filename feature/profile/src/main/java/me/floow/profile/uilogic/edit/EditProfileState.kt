package me.floow.profile.uilogic.edit

import me.floow.uikit.util.state.ValidatedField

interface EditProfileState {
	data class Edit(
		val name: ValidatedField = ValidatedField.Valid(""),
		val username: ValidatedField = ValidatedField.Valid(""),
		val bio: ValidatedField = ValidatedField.Valid("")
	) : EditProfileState

	data object Uploading : EditProfileState
}