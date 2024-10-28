package me.floow.mock.data

import me.floow.domain.api.models.EditProfileData
import me.floow.domain.data.GetDataResponse
import me.floow.domain.data.UpdateDataResponse
import me.floow.domain.data.repos.ProfileRepository
import me.floow.domain.models.SelfProfile
import me.floow.domain.values.ProfileDescription
import me.floow.domain.values.ProfileName

class MockProfileRepository : ProfileRepository {
	private var userProfile = SelfProfile(
		id = 1337L,
		name = ProfileName("Demn"),
		email = "demn@gmail.com",
		avatarUrl = "https://http.cat/images/200.jpg",
		description = ProfileDescription("Hi. My name is demn. I like coding: Kotlin, F#, Jetpack Compose, SwiftUI and etc. Welcome to my profile screen!")
	)

	override suspend fun getSelfData(): GetDataResponse<SelfProfile> {
		return GetDataResponse.Success(
			data = userProfile
		)
	}

	override suspend fun edit(data: EditProfileData): UpdateDataResponse {
		userProfile = userProfile.copy(
			description = data.description,
			name = data.name
		)

		return UpdateDataResponse.Success
	}
}