package me.floow.domain.values

import me.floow.domain.values.util.ValidationError
import me.floow.domain.values.util.ValueValidationResult

@JvmInline
value class ProfileDescription(
	val value: String
) {
	init {
		check(value.length <= 140)
	}

	companion object {
		fun create(value: String): ValueValidationResult<ProfileDescription> {
			return if (value.length > 140) {
				ValueValidationResult.Invalid(ValidationError.TooLarge)
			} else {
				ValueValidationResult.Valid(ProfileDescription(value))
			}
		}
	}
}
