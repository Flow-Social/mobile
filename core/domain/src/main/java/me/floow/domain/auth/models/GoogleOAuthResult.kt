package me.floow.domain.auth.models

sealed interface GoogleOAuthResult {
    data class Success(
        val idToken: String,
    ) : GoogleOAuthResult

    data object Failure : GoogleOAuthResult
}
