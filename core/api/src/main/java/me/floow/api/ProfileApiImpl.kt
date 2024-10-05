package me.floow.api

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.network.*
import me.floow.api.util.ApiConfig
import me.floow.api.util.HttpClientProvider
import me.floow.api.util.JsonSerializer
import me.floow.api.util.extensions.addAuthTokenHeader
import me.floow.api.util.safeApiCall
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
		return safeApiCall(errorResponse = GetSelfResponse.Error) {
			val authToken = authenticationManager.getAuthTokenOrNull() ?: return@safeApiCall GetSelfResponse.Error

			val response = httpClient.post("${config.apiUrl}/user/get/personal/data") {
				addAuthTokenHeader(authToken)
			}

			if (!response.status.isSuccess()) return@safeApiCall GetSelfResponse.Error

			val parsed = JsonSerializer.decodeFromString<GetSelfJsonResponse>(response.bodyAsText())

			return@safeApiCall GetSelfResponse.Success(
				id = parsed.data.id,
				name = parsed.data.name,
				email = parsed.data.email,
				avatarUrl = parsed.data.picture,
				biography = parsed.data.biography,
			)
		}
	}

	override suspend fun edit(data: EditProfileData): EditProfileResponse {
		return safeApiCall(errorResponse = EditProfileResponse(status = EditProfileResponseStatus.ERROR)) {
			val authToken = authenticationManager.getAuthTokenOrNull()
				?: return@safeApiCall EditProfileResponse(EditProfileResponseStatus.ERROR)

			val response = httpClient.post("${config.apiUrl}/user/get/personal/edit") {
				addAuthTokenHeader(authToken)
			}

			if (!response.status.isSuccess()) return@safeApiCall EditProfileResponse(EditProfileResponseStatus.ERROR)

			return@safeApiCall EditProfileResponse(EditProfileResponseStatus.SUCCESS)
		}
	}

	override suspend fun deleteProfile(): DeleteProfileResponse {
		return safeApiCall(errorResponse = DeleteProfileResponse(DeleteProfileResponseStatus.SUCCESS)) {
			val authToken = authenticationManager.getAuthTokenOrNull()
				?: return@safeApiCall DeleteProfileResponse(DeleteProfileResponseStatus.ERROR)

			val response = httpClient.post("${config.apiUrl}/user/get/personal/delete") {
				addAuthTokenHeader(authToken)
			}

			if (!response.status.isSuccess()) return@safeApiCall DeleteProfileResponse(DeleteProfileResponseStatus.ERROR)

			return@safeApiCall DeleteProfileResponse(DeleteProfileResponseStatus.SUCCESS)
		}
	}
}