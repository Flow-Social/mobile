package me.floow.domain.api.models

import me.floow.domain.values.ProfileDescription
import me.floow.domain.values.ProfileName
import me.floow.domain.values.ProfileUsername

data class EditProfileData(
	val name: ProfileName,
	val username: ProfileUsername,
	val description: ProfileDescription,
)
