package me.floow.login.uilogic

enum class ValidationErrorType {
    ShouldNotBeEmpty,
    TextTooLong,
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

interface CreateProfileState {
    data class Edit(
        val name: ValidatedField = ValidatedField.Valid(""),
        val username: ValidatedField = ValidatedField.Valid(""),
        val bio: ValidatedField = ValidatedField.Valid("")
    ) : CreateProfileState

    data object Uploading : CreateProfileState
}