package com.flowme.domain.auth

import com.flowme.domain.auth.models.GoogleOAuthResult

interface GoogleOAuth {
    suspend fun startSignIn()

    suspend fun handleGoogleOAuthCode(code: String): GoogleOAuthResult
}