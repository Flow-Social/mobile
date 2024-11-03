package me.floow.domain.values

import me.floow.domain.values.util.ValidationError
import me.floow.domain.values.util.ValueValidationResult

@JvmInline
value class ProfileUsername(
	val value: String
) {
	init {
		check(value.isNotBlank()) { "Should not be blank" }

		check(value.contains(Regex("^[a-zA-Z0-9_]+$"))) { "Should contain only a-z, A-Z, 0-9 and underscores" }

		check(value.length in 3..32) { "Length should be between 3 and 32 symbols" }
	}

	companion object {
		fun create(value: String): ValueValidationResult<ProfileUsername> {
			return when (value.length) {
				0 -> {
					ValueValidationResult.Invalid(ValidationError.ShouldNotBeBlank)
				}

				in 1..2 -> {
					ValueValidationResult.Invalid(ValidationError.TooSmall)
				}

				in 3..32 -> {
					if (!value.contains(Regex("^[a-zA-Z0-9_]+$"))) {
						ValueValidationResult.Invalid(ValidationError.ShouldNotContainIllegalCharacters)
					} else {
						ValueValidationResult.Valid(ProfileUsername(value))
					}
				}

				else -> {
					ValueValidationResult.Invalid(ValidationError.TooLarge)
				}
			}
		}
	}
}
