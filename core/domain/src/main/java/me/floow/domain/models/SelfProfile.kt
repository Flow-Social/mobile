package me.floow.domain.models

import me.floow.domain.values.ProfileDescription
import me.floow.domain.values.ProfileName
import me.floow.domain.values.ProfileUsername

data class SelfProfile(
	val name: ProfileName?,
	val username: ProfileUsername?,
	val avatarUrl: String?,
	val description: ProfileDescription?,
)
