package me.floow.domain.data.repos

import me.floow.domain.api.models.EditProfileData
import me.floow.domain.data.GetDataResponse
import me.floow.domain.data.UpdateDataResponse
import me.floow.domain.models.SelfProfile

interface ProfileRepository {
	suspend fun getSelfData(): GetDataResponse<SelfProfile>

	suspend fun edit(data: EditProfileData): UpdateDataResponse
}