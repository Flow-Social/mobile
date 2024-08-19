package com.flowme.auth

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.credentials.*
import androidx.credentials.exceptions.GetCredentialException
import com.flowme.auth.models.GoogleOAuthInfo
import com.flowme.domain.auth.GoogleOAuth
import com.flowme.domain.auth.models.GoogleOAuthResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException

class GoogleOAuthImpl(
    private val context: Context,
    private val googleOAuthInfo: GoogleOAuthInfo
) : GoogleOAuth {
    override suspend fun startSignIn(): GoogleOAuthResult {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(googleOAuthInfo.serverClientId)
            .setFilterByAuthorizedAccounts(true) // TODO
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val credentialManager = CredentialManager.create(context)

        try {
            val result = credentialManager.getCredential(
                request = request,
                context = context,
            )

            return handleCredentialResult(result)
        } catch (e: GetCredentialException) {
            Log.e(TAG, "GetCredentialException", e)
            return GoogleOAuthResult.Failure
        }
    }

    private fun handleCredentialResult(result: GetCredentialResponse) =
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)

                        GoogleOAuthResult.Success(
                            idToken = googleIdTokenCredential.idToken,
                            displayName = googleIdTokenCredential.displayName
                        )
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Received an invalid google id token response", e)
                        GoogleOAuthResult.Failure
                    }
                } else {
                    Log.e(TAG, "Unexpected type of credential")
                    GoogleOAuthResult.Failure
                }
            }

            else -> {
                Log.e(TAG, "Unexpected type of credential")
                GoogleOAuthResult.Failure
            }
        }

    override suspend fun signOut() { TODO() }
}

