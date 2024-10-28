package me.floow.domain.values

@JvmInline
value class ProfileUsername(
	val value: String
) {
	init {
		check(value.isNotBlank())

		check(value.contains(Regex("^[a-zA-Z0-9]+$")))

		check(value.length in 3..32)
	}
}
