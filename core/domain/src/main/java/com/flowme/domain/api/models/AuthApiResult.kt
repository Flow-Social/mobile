package com.flowme.domain.api.models

sealed interface AuthApiResult {
    data class Success(val token: String) : AuthApiResult

    data object Failure : AuthApiResult
}