package me.floow.domain.values

import me.floow.domain.values.util.RawValueObjectCreate
import me.floow.domain.values.util.ValidationError
import me.floow.domain.values.util.ValueValidationResult

@JvmInline
value class ProfileDescription private constructor(
	val value: String
) {
	companion object {
		fun create(value: String): ProfileDescription {
			check(value.length <= 140)

			return ProfileDescription(value)
		}

		fun createWithValidation(value: String): ValueValidationResult<ProfileDescription> {
			return if (value.length > 140) {
				ValueValidationResult.Invalid(ValidationError.TooLarge)
			} else {
				ValueValidationResult.Valid(ProfileDescription(value))
			}
		}

		@RawValueObjectCreate
		fun createRaw(value: String): ProfileDescription {
			return ProfileDescription(value)
		}
	}
}
