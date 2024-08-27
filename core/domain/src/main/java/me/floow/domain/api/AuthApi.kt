package me.floow.domain.api

import me.floow.domain.api.models.AuthApiResult

interface AuthApi {
    suspend fun getAuthTokenByGoogleIdToken(idToken: String): AuthApiResult
}