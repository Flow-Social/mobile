package me.floow.api

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import me.floow.api.util.ApiConfig
import me.floow.api.util.HttpClientProvider
import me.floow.api.util.JsonSerializer
import me.floow.api.util.extensions.addAuthTokenHeader
import me.floow.domain.api.ProfileApi
import me.floow.domain.api.models.*
import me.floow.domain.auth.AuthenticationManager

private data class GetSelfJsonResponse(
	val data: GetSelfResponseData,
)

private data class GetSelfResponseData(
	val id: Long,
	var name: String? = null,
	var email: String? = null,
	val picture: String? = null,
	var biography: String? = null
)

class ProfileApiImpl(
	private val config: ApiConfig,
	private val authenticationManager: AuthenticationManager,
	httpClientProvider: HttpClientProvider
) : ProfileApi {
	private val httpClient = httpClientProvider.getClient()

	override suspend fun getSelf(): GetSelfResponse {
		val authToken = authenticationManager.getAuthTokenOrNull() ?: return GetSelfResponse.Error

		val response = httpClient.post("${config.apiUrl}/user/get/personal/data") {
			addAuthTokenHeader(authToken)
		}

		if (!response.status.isSuccess()) return GetSelfResponse.Error

		val parsed = JsonSerializer.decodeFromString<GetSelfJsonResponse>(response.bodyAsText())

		return GetSelfResponse.Success(
			id = parsed.data.id,
			name = parsed.data.name,
			email = parsed.data.email,
			avatarUrl = parsed.data.picture,
			biography = parsed.data.biography,
		)
	}

	override suspend fun edit(data: EditProfileData): EditProfileResponse {
		val authToken = authenticationManager.getAuthTokenOrNull()
			?: return EditProfileResponse(EditProfileResponseStatus.ERROR)

		val response = httpClient.post("${config.apiUrl}/user/get/personal/edit") {
			addAuthTokenHeader(authToken)
		}

		if (!response.status.isSuccess()) return EditProfileResponse(EditProfileResponseStatus.ERROR)

		return EditProfileResponse(EditProfileResponseStatus.SUCCESS)
	}

	override suspend fun deleteProfile(): DeleteProfileResponse {
		val authToken = authenticationManager.getAuthTokenOrNull()
			?: return DeleteProfileResponse(DeleteProfileResponseStatus.ERROR)

		val response = httpClient.post("${config.apiUrl}/user/get/personal/delete") {
			addAuthTokenHeader(authToken)
		}

		if (!response.status.isSuccess()) return DeleteProfileResponse(DeleteProfileResponseStatus.ERROR)

		return DeleteProfileResponse(DeleteProfileResponseStatus.SUCCESS)
	}
}