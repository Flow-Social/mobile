package com.flowme.domain.auth.models

sealed interface AuthenticationResult {
    data class Success(
        val token: String
    ) : AuthenticationResult

    // TODO: add failure reasons enum
    data object Failure : AuthenticationResult
}
