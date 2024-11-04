package me.floow.domain.api

import me.floow.domain.api.models.DeleteProfileResponse
import me.floow.domain.api.models.EditProfileData
import me.floow.domain.api.models.EditProfileResponse
import me.floow.domain.api.models.GetSelfResponse

interface ProfileApi {
	suspend fun getSelf(): GetSelfResponse

	suspend fun edit(data: EditProfileData): EditProfileResponse

	suspend fun deleteProfile(): DeleteProfileResponse
}