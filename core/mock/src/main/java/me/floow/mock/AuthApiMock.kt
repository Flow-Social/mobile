package me.floow.mock

import me.floow.domain.api.AuthApi
import me.floow.domain.api.models.AuthApiResult

class AuthApiMock : AuthApi {
    override suspend fun getAuthTokenByGoogleIdToken(idToken: String): AuthApiResult {
        TODO("Not yet implemented")
    }
}