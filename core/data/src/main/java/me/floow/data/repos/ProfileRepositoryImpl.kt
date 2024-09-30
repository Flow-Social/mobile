package me.floow.data.repos

import me.floow.domain.api.ProfileApi
import me.floow.domain.data.repos.ProfileRepository
import me.floow.domain.models.SelfProfile

class ProfileRepositoryImpl(
	private val profileApi: ProfileApi,
) : ProfileRepository {
	override fun getSelfData(): SelfProfile {
		TODO("Not yet implemented")
	}
}