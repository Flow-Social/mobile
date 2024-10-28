package me.floow.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import me.floow.api.util.ApiConfig
import me.floow.api.util.HttpClientProvider
import me.floow.api.util.JsonSerializer
import me.floow.domain.api.AuthApi
import me.floow.domain.api.models.AuthApiResult
import me.floow.domain.utils.Logger

@Serializable
internal data class GoogleAuthorizeResponse(
	val status: String,
	val task: String,
	val message: String,
	val data: Data
)

@Serializable
internal data class Data(
	val token: String,
	val type: Int,
)

class AuthApiImpl(
	private val config: ApiConfig,
	private val logger: Logger,
	httpClientProvider: HttpClientProvider
) : AuthApi {
	private val httpClient = httpClientProvider.getClient()

	override suspend fun getAuthTokenByGoogleIdToken(idToken: String): AuthApiResult =
		withContext(Dispatchers.IO) {
			logger.d("getAuthTokenByGoogleIdToken", "POST \"${config.apiUrl}/auth/social/google\"")

			val response = httpClient.post("${config.apiUrl}/auth/social/google") {
				contentType(ContentType.Application.FormUrlEncoded)

				setBody(
					FormDataContent(
						Parameters.build {
							append("id_token", idToken)
							append("type", "mobile")
						}
					)
				)
			}

			val bodyText = response.bodyAsText()

			logger.d("getAuthTokenByGoogleIdToken", "Body text: $bodyText")

			if (response.status.isSuccess()) {
				val jsonParser = JsonSerializer
				val parsedResponse = jsonParser.decodeFromString<GoogleAuthorizeResponse>(bodyText)

				return@withContext AuthApiResult.Success(
					token = parsedResponse.data.token,
					isRegistration = when (parsedResponse.data.type) {
						0 -> true
						else -> false
					}
				)
			} else {
				logger.d("getAuthTokenByGoogleIdToken", "Failure: ${response.status}")
			}

			return@withContext AuthApiResult.Failure
		}
}