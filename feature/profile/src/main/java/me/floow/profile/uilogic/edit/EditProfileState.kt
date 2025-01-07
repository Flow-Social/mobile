package me.floow.profile.uilogic.edit

import android.net.Uri
import me.floow.uikit.util.state.ValidatedField

interface EditProfileState {
	data class Edit(
		val name: ValidatedField = ValidatedField.Valid(""),
		val username: ValidatedField = ValidatedField.Valid(""),
		val bio: ValidatedField = ValidatedField.Valid(""),
		val avatarUri: Uri? = null,
	) : EditProfileState

	data object Uploading : EditProfileState
}