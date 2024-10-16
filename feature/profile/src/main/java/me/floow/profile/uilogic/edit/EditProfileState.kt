package me.floow.profile.uilogic.edit

enum class ValidationErrorType {
	ShouldNotBeEmpty,
	Other
}

interface ValidatedField {
	val value: String

	data class Valid(
		override val value: String
	) : ValidatedField

	data class Invalid(
		override val value: String,
		val errorType: ValidationErrorType
	) : ValidatedField
}

interface EditProfileState {
	data class Edit(
		val name: ValidatedField = ValidatedField.Valid(""),
		val username: ValidatedField = ValidatedField.Valid(""),
		val bio: ValidatedField = ValidatedField.Valid("")
	) : EditProfileState

	data object Uploading : EditProfileState
}