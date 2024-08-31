package me.floow.auth

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import me.floow.domain.api.AuthApi
import me.floow.domain.api.models.AuthApiResult
import me.floow.domain.auth.AuthenticationManager
import me.floow.domain.auth.GoogleOAuth
import me.floow.domain.auth.models.AuthState
import me.floow.domain.auth.models.AuthenticationResult
import me.floow.domain.auth.models.GoogleOAuthResult

class AuthenticationManagerImpl(
    context: Context,
    private val googleOAuth: GoogleOAuth,
    private val authApi: AuthApi,
) : AuthenticationManager {
    companion object {
        private const val AUTH_SHARED_PREF = "flowme.auth"
        private const val AUTH_TOKEN_PREF_KEY = "authToken"
    }

    private val _authenticationStateFlow: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.NoIdToken)
    override val authenticationStateFlow: StateFlow<AuthState> = _authenticationStateFlow

    private val sharedPreferences: SharedPreferences = context
        .getSharedPreferences(
            AUTH_SHARED_PREF,
            Context.MODE_PRIVATE
        )

    override suspend fun handleGoogleOAuthCode(code: String) {
        _authenticationStateFlow.update {
            AuthState.HandlingOAuth
        }

        when (val googleOAuthResult = googleOAuth.handleGoogleOAuthCode(code)) {
            is GoogleOAuthResult.Success -> {
                val authApiResult = authApi.getAuthTokenByGoogleIdToken(googleOAuthResult.idToken)

                if (authApiResult is AuthApiResult.Success) {
                    _authenticationStateFlow.update {
                        AuthState.HasResult(AuthenticationResult.Success(authApiResult.token))
                    }
                    writeAuthToken(authApiResult.token)
                } else {
                    _authenticationStateFlow.update {
                        AuthState.HasResult(AuthenticationResult.Failure)
                    }
                }
            }

            is GoogleOAuthResult.Failure -> {
                _authenticationStateFlow.update {
                    AuthState.HasResult(AuthenticationResult.Failure)
                }
            }
        }
    }

    private fun writeAuthToken(token: String) {
        sharedPreferences.edit().apply {
            putString(AUTH_TOKEN_PREF_KEY, token)
            apply()
        }
    }

    override suspend fun getAuthTokenOrNull(): String? {
        return sharedPreferences.getString(AUTH_TOKEN_PREF_KEY, null)
    }

    override fun isSignedIn(): Boolean {
        return sharedPreferences.getString(AUTH_TOKEN_PREF_KEY, null) != null
    }

    override suspend fun startGoogleAuthentication() {
        googleOAuth.startSignIn()
    }
}