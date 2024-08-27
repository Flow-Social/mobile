package me.floow.domain.auth

import me.floow.domain.auth.models.GoogleOAuthResult

interface GoogleOAuth {
    suspend fun startSignIn()

    suspend fun handleGoogleOAuthCode(code: String): GoogleOAuthResult
}