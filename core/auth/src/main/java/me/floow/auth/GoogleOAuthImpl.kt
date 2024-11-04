package me.floow.auth

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import me.floow.auth.models.GoogleOAuthInfo
import me.floow.domain.auth.GoogleOAuth
import me.floow.domain.auth.models.GoogleOAuthResult
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

@Serializable
internal data class IdTokenResponse(
    @SerialName("id_token")
    val idToken: String
)

class GoogleOAuthImpl(
    private val context: Context,
    private val googleOAuthInfo: GoogleOAuthInfo
) : GoogleOAuth {
    companion object {
        private const val STRING_LENGTH = 43
        private const val TOKEN_URL = "https://oauth2.googleapis.com/token"
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) { json() }
    }
    private val json = Json { ignoreUnknownKeys = true }

    private val codeVerifier: String = generateCodeVerifier()

    private fun generateCodeVerifier(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val secureRandom = SecureRandom()
        val str = (1..STRING_LENGTH).map {
            secureRandom.nextInt(charPool.size).let { charPool[it] }
        }.joinToString("")

        return Base64.getUrlEncoder().withoutPadding()
            .encodeToString(str.toByteArray())
    }

    override suspend fun startSignIn() {
        val md = MessageDigest.getInstance("SHA-256")
        val codeChallenge = Base64.getUrlEncoder().withoutPadding()
            .encodeToString(md.digest(codeVerifier.toByteArray()))

        val authUrl = Uri.parse(
            "https://accounts.google.com/o/oauth2/v2/auth" +
                    "?client_id=${googleOAuthInfo.clientId}" +
                    "&redirect_uri=com.flowme.flow:/" +
                    "&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile" +
                    "&code_challenge=$codeChallenge" +
                    "&code_challenge_method=S256" +
                    "&response_type=code"
        )

        val builder = CustomTabsIntent.Builder().apply {
            setShowTitle(true)
            setInstantAppsEnabled(true)
        }

        val customBuilder = builder.build()
        customBuilder.launchUrl(context, authUrl)
    }

    override suspend fun handleGoogleOAuthCode(code: String): GoogleOAuthResult {
        val response: HttpResponse = httpClient.post(TOKEN_URL) {
            contentType(ContentType.Application.FormUrlEncoded)

            setBody(
                listOf(
                    "code" to code,
                    "client_id" to googleOAuthInfo.clientId,
                    "code_verifier" to codeVerifier,
                    "grant_type" to "authorization_code",
                    "redirect_uri" to googleOAuthInfo.redirectUri
                ).formUrlEncode()
            )
        }

        if (response.status.isSuccess()) {
            val idTokenResponse = json.decodeFromString<IdTokenResponse>(response.bodyAsText())

            return GoogleOAuthResult.Success(
                idToken = idTokenResponse.idToken
            )
        }

        return GoogleOAuthResult.Failure
    }
}
