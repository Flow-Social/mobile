package me.floow.uikit.util.state

enum class ValidationErrorType {
    ShouldNotBeEmpty,
    TextTooLong,
    Other
}

interface ValidatedField {
    val value: String

    companion object {
        val initialField = Valid("")
    }

    data class Valid(
        override val value: String
    ) : ValidatedField

    data class Invalid(
        override val value: String,
        val errorType: ValidationErrorType? = null
    ) : ValidatedField
}