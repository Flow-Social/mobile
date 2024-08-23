package com.flowme.domain.auth

import com.flowme.domain.auth.models.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthenticationManager {
    val authenticationStateFlow: StateFlow<AuthState>

    suspend fun startGoogleAuthentication()

    suspend fun handleGoogleOAuthCode(code: String)

    suspend fun getAuthTokenOrNull(): String?
}