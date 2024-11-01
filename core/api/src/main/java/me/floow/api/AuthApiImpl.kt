package me.floow.api

import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import me.floow.api.util.ApiConfig
import me.floow.api.util.HttpClientProvider
import me.floow.api.util.JsonSerializer
import me.floow.api.util.extensions.logKtorRequest
import me.floow.domain.api.AuthApi
import me.floow.domain.api.models.AuthApiResult
import me.floow.domain.utils.Logger

@Serializable
internal data class GoogleAuthorizeResponse(
	val data: Data
)

@Serializable
internal data class Data(
	val token: String,
	val status: Int
)

class AuthApiImpl(
	private val config: ApiConfig,
	private val logger: Logger,
	httpClientProvider: HttpClientProvider
) : AuthApi {
	private val httpClient = httpClientProvider.getClient()

	override suspend fun getAuthTokenByGoogleIdToken(idToken: String): AuthApiResult =
		withContext(Dispatchers.IO) {
			val response = httpClient.post("${config.apiUrl}/auth/google") {
				contentType(ContentType.Application.FormUrlEncoded)

				setBody(
					FormDataContent(
						Parameters.build {
							append("type", "mobile")
							append("id_token", idToken)
 						}
					)
				)
			}

			logger.logKtorRequest("getAuthTokenByGoogleIdToken", response.request)

			val bodyText = response.bodyAsText()

			logger.d("getAuthTokenByGoogleIdToken", "Body text: $bodyText")

			if (response.status.isSuccess()) {
				val jsonParser = JsonSerializer
				val parsedResponse = jsonParser.decodeFromString<GoogleAuthorizeResponse>(bodyText)

				return@withContext AuthApiResult.Success(
					token = parsedResponse.data.token,
					isRegistration = when (parsedResponse.data.status) {
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