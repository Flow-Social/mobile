package com.flowme.domain.auth

import com.flowme.domain.auth.models.AuthenticationResult

interface AuthenticationManager {
    suspend fun getAuthTokenByGoogleOAuth(): AuthenticationResult
}