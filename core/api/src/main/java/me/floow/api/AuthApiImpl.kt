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

@Serializable
internal data class GoogleAuthorizeResponse(
    val status: String,
    val task: String,
    val message: String,
    val data: Data
)

@Serializable
internal data class Data(
    val token: String
)

class AuthApiImpl(
    private val config: ApiConfig,
    httpClientProvider: HttpClientProvider
) : AuthApi {
    private val httpClient = httpClientProvider.getClient()

    override suspend fun getAuthTokenByGoogleIdToken(idToken: String): AuthApiResult = withContext(Dispatchers.IO) {
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

        if (response.status.isSuccess()) {
            val jsonParser = JsonSerializer
            val parsedResponse = jsonParser.decodeFromString<GoogleAuthorizeResponse>(bodyText)

            return@withContext AuthApiResult.Success(
                token = parsedResponse.data.token
            )
        } else {
            println(response.status)
            println(bodyText)
        }

        return@withContext AuthApiResult.Failure
    }
}