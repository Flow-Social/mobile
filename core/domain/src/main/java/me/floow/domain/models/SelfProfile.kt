package me.floow.domain.models

import me.floow.domain.values.ProfileDescription
import me.floow.domain.values.ProfileName

data class SelfProfile(
	val id: Long,
	val name: ProfileName?,
	val email: String?,
	val avatarUrl: String?,
	val description: ProfileDescription?,
)
