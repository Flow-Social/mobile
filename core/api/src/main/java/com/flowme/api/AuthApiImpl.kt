package com.flowme.api

import com.flowme.domain.api.AuthApi
import com.flowme.domain.api.models.AuthApiResult
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

data class AuthApiConfig(
    val apiUrl: String
)

class AuthApiImpl(
    private val config: AuthApiConfig
) : AuthApi {
    private val httpClient = HttpClient(CIO)

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
            val parsedResponse = Json.decodeFromString<GoogleAuthorizeResponse>(bodyText)

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