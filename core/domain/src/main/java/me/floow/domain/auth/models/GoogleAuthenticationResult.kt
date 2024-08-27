package me.floow.domain.auth.models

sealed interface GoogleAuthenticationResult {
    data class Success(
        val idToken: String
    ) : GoogleAuthenticationResult

    data object Failure : GoogleAuthenticationResult
}
