package com.flowme.api

import com.flowme.domain.api.AuthApi
import com.flowme.domain.api.models.AuthApiResult
import com.flowme.domain.auth.AuthenticationManager
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URLEncoder

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
    private val apiUrl = "https://floow.me/api"

    override suspend fun getAuthTokenByGoogleIdToken(idToken: String): AuthApiResult = withContext(Dispatchers.IO) {
        val response = httpClient.get {
            url("$apiUrl/auth/social/google?code=${URLEncoder.encode(idToken, "UTF-8")}")

            setBody(FormDataContent(Parameters.build {
                append("code", idToken)
            }))

            contentType(ContentType.Application.FormUrlEncoded)

            println(url)
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