package me.floow.data.repos

import me.floow.domain.api.ProfileApi
import me.floow.domain.api.models.EditProfileData
import me.floow.domain.api.models.EditProfileResponseStatus
import me.floow.domain.api.models.GetSelfResponse
import me.floow.domain.data.GetDataError
import me.floow.domain.data.GetDataResponse
import me.floow.domain.data.UpdateDataResponse
import me.floow.domain.data.repos.ProfileRepository
import me.floow.domain.models.SelfProfile
import me.floow.domain.values.ProfileDescription
import me.floow.domain.values.ProfileName

class ProfileRepositoryImpl(
	private val profileApi: ProfileApi,
) : ProfileRepository {
	override suspend fun getSelfData(): GetDataResponse<SelfProfile> {
		when (val selfData = profileApi.getSelf()) {
			is GetSelfResponse.Success -> {
				return GetDataResponse.Success(
					SelfProfile(
						id = selfData.id,
						name = selfData.name?.let { ProfileName(it) },
						email = selfData.email,
						description = selfData.biography?.let { ProfileDescription(it) },
						avatarUrl = selfData.avatarUrl,
					)
				)
			}

			is GetSelfResponse.Error -> {
				return GetDataResponse.Error(error = GetDataError.Other)
			}
		}
	}

	override suspend fun edit(data: EditProfileData): UpdateDataResponse {
		val result = profileApi.edit(
			data = data
		)

		return when (result.status) {
			EditProfileResponseStatus.ERROR -> UpdateDataResponse.Failure()

			EditProfileResponseStatus.SUCCESS -> UpdateDataResponse.Success
		}
	}
}