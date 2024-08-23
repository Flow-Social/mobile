package com.flowme.domain.auth.models

sealed interface AuthState {
    data object NoIdToken : AuthState

    data class HasResult(val authenticationResult: AuthenticationResult) : AuthState
}