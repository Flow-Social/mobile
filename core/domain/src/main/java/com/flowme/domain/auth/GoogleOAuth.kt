package com.flowme.domain.auth

import com.flowme.domain.auth.models.GoogleOAuthResult

interface GoogleOAuth {
    suspend fun startSignIn(): GoogleOAuthResult

    suspend fun signOut()
}