package me.floow.domain.values

@JvmInline
value class ProfileDescription(
	val value: String
) {
	init {
		check(value.length <= 140)
	}
}
