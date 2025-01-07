package me.floow.uikit.util.state

/// Перечисление возможных состояний валидации значения поля
enum class ValidationErrorType {
    ShouldNotBeEmpty,
    TextTooLong,
    Other
}

/// Интерфейс поля с валидацией значения
interface ValidatedField {
    /// Значение поля с валидацией
    val value: String

    companion object {
        val initialField = Valid("")
    }

    /// Поле со значением без ошибки валидации
    data class Valid(
        override val value: String
    ) : ValidatedField

    /// Поле со значением с ошибкой валидации
    data class Invalid(
        override val value: String,
        val errorType: ValidationErrorType? = null
    ) : ValidatedField
}