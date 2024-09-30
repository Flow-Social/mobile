package me.floow.domain.models

data class SelfProfile(
	val id: Long,
	val name: String?,
	val email: String?,
	val avatarUrl: String?,
	val biography: String?,
)
