package me.floow.mock.auth

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import me.floow.domain.auth.AuthenticationManager
import me.floow.domain.auth.models.AuthState
import me.floow.domain.auth.models.AuthenticationResult
import me.floow.mock.auth.data.mockToken

class MockAuthenticationManager : AuthenticationManager {
    private val _authenticationStateFlow = MutableStateFlow<AuthState>(AuthState.NoIdToken)
    override val authenticationStateFlow: StateFlow<AuthState> = _authenticationStateFlow

    override suspend fun startGoogleAuthentication() {
        delay(500L)

        handleGoogleOAuthCode("some code")
    }

    override suspend fun handleGoogleOAuthCode(code: String) {
        _authenticationStateFlow.update {
            AuthState.HandlingOAuth
        }

        delay(1000L)

        _authenticationStateFlow.update {
            AuthState.HasResult(
                AuthenticationResult.Success(
                    token = mockToken,
                    isRegistration = true
                )
            )
        }
    }

    override suspend fun getAuthTokenOrNull(): String? = null

    override fun isSignedIn(): Boolean = false
}