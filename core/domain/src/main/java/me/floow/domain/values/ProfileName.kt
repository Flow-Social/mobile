package me.floow.domain.values

@JvmInline
value class ProfileName(
	val value: String
) {
	init {
		check(value.isNotBlank())

		check(!value.contains(Regex("[<>&\"']")))

		check(value.length in 1..32)
	}
}
