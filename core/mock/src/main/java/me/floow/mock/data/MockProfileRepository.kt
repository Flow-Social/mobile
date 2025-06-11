package me.floow.mock.data

import me.floow.domain.api.models.EditProfileData
import me.floow.domain.data.GetDataResponse
import me.floow.domain.data.UpdateDataResponse
import me.floow.domain.data.repos.ProfileRepository
import me.floow.domain.models.SelfProfile
import me.floow.domain.values.ProfileDescription
import me.floow.domain.values.ProfileName
import me.floow.domain.values.ProfileUsername
import me.floow.domain.values.util.RawValueObjectCreate

class MockProfileRepository : ProfileRepository {
	@OptIn(RawValueObjectCreate::class)
	private var userProfile = SelfProfile(
		name = ProfileName.createRaw("Demn"),
		avatarUrl = "https://http.cat/images/200.jpg",
		username = ProfileUsername.createRaw("demndevel"),
		description = ProfileDescription.createRaw("Hi. My name is demn. I like coding: Kotlin, F#, Jetpack Compose, SwiftUI and etc. Welcome to my profile screen!")
	)

	fun getAvatarUrl(): String {
		return "https://http.cat/images/101.jpg"
	}

	override suspend fun getSelfData(): GetDataResponse<SelfProfile> {
		return GetDataResponse.Success(
			data = userProfile
		)
	}

	override suspend fun edit(data: EditProfileData): UpdateDataResponse {
		userProfile = userProfile.copy(
			description = data.description,
			username = data.username,
			name = data.name
		)

		return UpdateDataResponse.Success
	}

	override suspend fun uploadAvatar(
		avatarBytes: ByteArray
	): UpdateDataResponse {
		return UpdateDataResponse.Success
	}
}
