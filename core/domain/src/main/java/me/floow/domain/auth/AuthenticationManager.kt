package me.floow.domain.auth

import kotlinx.coroutines.flow.StateFlow
import me.floow.domain.auth.models.AuthState

interface AuthenticationManager {
    val authenticationStateFlow: StateFlow<AuthState>

    suspend fun startGoogleAuthentication()

    suspend fun handleGoogleOAuthCode(code: String)

    suspend fun getAuthTokenOrNull(): String?
}