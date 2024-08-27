package com.flowme.domain.auth.models

sealed interface AuthState {
    data object NoIdToken : AuthState

    data object HandlingOAuth : AuthState

    data class HasResult(val authenticationResult: AuthenticationResult) : AuthState
}