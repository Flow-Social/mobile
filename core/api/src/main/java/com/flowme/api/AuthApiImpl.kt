package com.flowme.api

import com.flowme.domain.api.AuthApi
import com.flowme.domain.api.models.AuthApiResult
import com.flowme.domain.auth.AuthenticationManager
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
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

class AuthApiImpl : AuthApi {
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }
    private val apiUrl = "https://floow.me/api/"

    override suspend fun getAuthTokenByGoogleIdToken(idToken: String): AuthApiResult = withContext(Dispatchers.IO) {
        val response = httpClient.post {
            url("$apiUrl/auth/social/google")

            contentType(ContentType.Application.FormUrlEncoded)
            setBody(Parameters.build {
                append("code", idToken)
            })
        }

        if (response.status.isSuccess()) {
            val parsedResponse = Json.decodeFromString<GoogleAuthorizeResponse>(response.bodyAsText())

            return@withContext AuthApiResult.Success(
                token = parsedResponse.data.token
            )
        }

        return@withContext AuthApiResult.Failure
    }
}