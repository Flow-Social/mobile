package me.floow.domain.values.util

enum class ValidationError {
	ShouldNotBeBlank,
	ShouldNotContainIllegalCharacters,
	TooSmall,
	TooLarge,
}