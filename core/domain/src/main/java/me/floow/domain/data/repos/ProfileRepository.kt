package me.floow.domain.data.repos

import me.floow.domain.data.GetDataResponse
import me.floow.domain.models.SelfProfile

interface ProfileRepository {
	suspend fun getSelfData(): GetDataResponse<SelfProfile>
}