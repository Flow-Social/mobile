package me.floow.domain.api.models

sealed interface AuthApiResult {
    data class Success(
        val token: String,
        val isRegistration: Boolean,
    ) : AuthApiResult

    data object Failure : AuthApiResult
}