package com.flowme.domain.api

import com.flowme.domain.api.models.AuthApiResult

interface AuthApi {
    suspend fun getAuthTokenByGoogleIdToken(idToken: String): AuthApiResult
}