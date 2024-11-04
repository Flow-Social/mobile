package me.floow.domain.values.util

interface ValueValidationResult<T> {
	data class Valid<T>(val value: T) : ValueValidationResult<T>

	data class Invalid<T>(val error: ValidationError) : ValueValidationResult<T>
}