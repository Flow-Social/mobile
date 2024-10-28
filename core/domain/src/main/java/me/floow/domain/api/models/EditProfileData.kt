package me.floow.domain.api.models

import me.floow.domain.values.ProfileDescription
import me.floow.domain.values.ProfileName

data class EditProfileData(
	val name: ProfileName,
	val description: ProfileDescription,
)
