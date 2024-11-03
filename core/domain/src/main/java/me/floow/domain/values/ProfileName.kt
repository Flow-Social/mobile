package me.floow.domain.values

import me.floow.domain.values.util.ValidationError
import me.floow.domain.values.util.ValueValidationResult

@JvmInline
value class ProfileName(
	val value: String
) {
	init {
		check(value.isNotBlank()) { "Should not be blank" }

		check(!value.contains(Regex("[<>&\"']"))) { "Should not contain '<', '>', '&', '\"' and '''" }

		check(value.length in 1..32) { "Length should be between 1 and 32 symbols" }
	}

	companion object {
		fun create(value: String): ValueValidationResult<ProfileName> {
			return when (value.length) {
				0 -> {
					ValueValidationResult.Invalid(ValidationError.ShouldNotBeBlank)
				}

				in 1..32 -> {
					if (value.contains(Regex("[<>&\"']"))) {
						ValueValidationResult.Invalid(ValidationError.ShouldNotBeBlank)
					} else {
						ValueValidationResult.Valid(ProfileName(value))
					}
				}

				else -> {
					ValueValidationResult.Invalid(ValidationError.TooLarge)
				}
			}
		}
	}
}
