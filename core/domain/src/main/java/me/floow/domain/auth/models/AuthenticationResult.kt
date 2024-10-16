package me.floow.domain.auth.models

sealed interface AuthenticationResult {
    data class Success(
        val token: String,
        val isRegistration: Boolean
    ) : AuthenticationResult

    // TODO: add failure reasons enum
    data object Failure : AuthenticationResult
}
