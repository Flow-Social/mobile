package com.flowme.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.flowme.domain.api.AuthApi
import com.flowme.domain.api.models.AuthApiResult
import com.flowme.domain.auth.AuthenticationManager
import com.flowme.domain.auth.GoogleOAuth
import com.flowme.domain.auth.models.AuthenticationResult
import com.flowme.domain.auth.models.GoogleAuthenticationResult
import com.flowme.domain.auth.models.GoogleOAuthResult

class AuthenticationManagerImpl(
    context: Context,
    private val googleOAuth: GoogleOAuth,
    private val authApi: AuthApi
) : AuthenticationManager {
    companion object {
        private const val AUTH_SHARED_PREF = "flowme.auth"
        private const val AUTH_TOKEN_PREF_KEY = "authToken"
    }

    private val sharedPreferences: SharedPreferences = context
        .getSharedPreferences(
            AUTH_SHARED_PREF,
            Context.MODE_PRIVATE
        )

    private suspend fun getGoogleIdToken(): GoogleAuthenticationResult {
        val googleOAuthResult = googleOAuth.startSignIn()

        if (googleOAuthResult is GoogleOAuthResult.Success) {
            val idToken = googleOAuthResult.idToken

            return GoogleAuthenticationResult.Success(idToken)
        }
        return GoogleAuthenticationResult.Failure
    }

    override suspend fun getAuthTokenByGoogleOAuth(): AuthenticationResult {
        val authTokenFromPreferences = sharedPreferences.getString(AUTH_TOKEN_PREF_KEY, null)

        if (authTokenFromPreferences != null) return AuthenticationResult.Success(authTokenFromPreferences)

        val googleAuthenticationResult = getGoogleIdToken()

        if (googleAuthenticationResult is GoogleAuthenticationResult.Success) {
            val authApiResult = authApi.getAuthTokenByGoogleIdToken(googleAuthenticationResult.idToken)

            if (authApiResult is AuthApiResult.Success) {
                return AuthenticationResult.Success(authApiResult.token)
            }
        }

        return AuthenticationResult.Failure
    }
}