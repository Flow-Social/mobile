package com.flowme.domain.auth.models

sealed interface GoogleOAuthResult {
    data class Success(
        val idToken: String,
        val displayName: String?
    ) : GoogleOAuthResult

    data object Failure : GoogleOAuthResult
}
