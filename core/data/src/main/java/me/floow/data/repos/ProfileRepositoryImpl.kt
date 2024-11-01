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
import me.floow.domain.utils.Logger
import me.floow.domain.values.ProfileDescription
import me.floow.domain.values.ProfileName
import me.floow.domain.values.ProfileUsername

class ProfileRepositoryImpl(
	private val logger: Logger,
	private val profileApi: ProfileApi,
) : ProfileRepository {
	override suspend fun getSelfData(): GetDataResponse<SelfProfile> {
		return when (val selfData = profileApi.getSelf()) {
			is GetSelfResponse.Success -> {
				logger.d("ProfileRepositoryImpl.getSelfData", "Success response: $selfData")

				GetDataResponse.Success(
					SelfProfile(
						name = selfData.name?.let { ProfileName(it) },
						username = selfData.username?.let { ProfileUsername(it) },
						description = selfData.biography?.let { ProfileDescription(it) },
						avatarUrl = selfData.avatarUrl,
					)
				)
			}

			is GetSelfResponse.Error -> {
				logger.d("ProfileRepositoryImpl.getSelfData", "Failure response: $selfData")

				GetDataResponse.Error(error = GetDataError.Other)
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