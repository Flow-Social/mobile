package me.floow.api

import io.ktor.client.request.*
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.network.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.floow.api.util.ApiConfig
import me.floow.api.util.HttpClientProvider
import me.floow.api.util.JsonSerializer
import me.floow.api.util.extensions.addAuthTokenHeader
import me.floow.api.util.extensions.logFailureResponse
import me.floow.api.util.extensions.logKtorRequest
import me.floow.api.util.safeApiCall
import me.floow.domain.api.ProfileApi
import me.floow.domain.api.models.*
import me.floow.domain.auth.AuthenticationManager
import me.floow.domain.utils.Logger

@Serializable
private data class GetSelfJsonResponse(
	val data: GetSelfResponseData,
)

@Serializable
private data class GetSelfResponseData(
	var name: String? = null,
	var username: String? = null,
	@SerialName("picture_url")
	val pictureUrl: String? = null,
	var bio: String? = null
)

class ProfileApiImpl(
	private val config: ApiConfig,
	private val logger: Logger,
	private val authenticationManager: AuthenticationManager,
	httpClientProvider: HttpClientProvider
) : ProfileApi {
	private val httpClient = httpClientProvider.getClient()

	override suspend fun getSelf(): GetSelfResponse {
		return safeApiCall(errorResponse = GetSelfResponse.Error) {
			val authToken = authenticationManager.getAuthTokenOrNull()
				?: return@safeApiCall GetSelfResponse.Error

			val response = httpClient.get("${config.apiUrl}/user") {
				addAuthTokenHeader(authToken)
			}

			logger.logKtorRequest("ProfileApiImpl getSelf", response.request)

			val bodyText = response.bodyAsText()

			if (!response.status.isSuccess()) {
				logger.logFailureResponse("ProfileApiImpl getSelf", response.status, bodyText)

				return@safeApiCall GetSelfResponse.Error
			}

			logger.d("ProfileApiImpl getSelf", "Body text: $bodyText")

			val parsed = JsonSerializer.decodeFromString<GetSelfJsonResponse>(bodyText)

			return@safeApiCall GetSelfResponse.Success(
				name = parsed.data.name,
				username = parsed.data.username,
				avatarUrl = parsed.data.pictureUrl,
				biography = parsed.data.bio,
			)
		}
	}

	override suspend fun edit(data: EditProfileData): EditProfileResponse {
		return safeApiCall(errorResponse = EditProfileResponse(status = EditProfileResponseStatus.ERROR)) {
			val authToken = authenticationManager.getAuthTokenOrNull()
				?: return@safeApiCall EditProfileResponse(EditProfileResponseStatus.ERROR)

			val response = httpClient.put("${config.apiUrl}/user") {
				addAuthTokenHeader(authToken)
				contentType(ContentType.Application.FormUrlEncoded)
				setBody(FormDataContent(
					parameters {
						append("name", data.name.value)
						append("username", data.username.value)
						append("bio", data.description.value)
					}
				))
			}

			logger.logKtorRequest("ProfileApiImpl edit", response.request)

			val bodyText = response.bodyAsText()

			if (!response.status.isSuccess()) {
				logger.logFailureResponse("ProfileApiImpl edit", response.status, bodyText)

				return@safeApiCall EditProfileResponse(EditProfileResponseStatus.ERROR)
			}

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

			if (!response.status.isSuccess()) return@safeApiCall DeleteProfileResponse(
				DeleteProfileResponseStatus.ERROR
			)

			return@safeApiCall DeleteProfileResponse(DeleteProfileResponseStatus.SUCCESS)
		}
	}
}