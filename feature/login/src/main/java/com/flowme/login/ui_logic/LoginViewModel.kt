package com.flowme.login.ui_logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flowme.domain.auth.AuthenticationManager
import com.flowme.domain.auth.models.AuthenticationResult
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authenticationManager: AuthenticationManager
) : ViewModel() {
    fun loginWithGoogle(
        onGoToHome: () -> Unit,
        onFailure: () -> Unit,
    ) {
        viewModelScope.launch {
            val result = authenticationManager.getAuthTokenByGoogleOAuth()

            when (result) {
                is AuthenticationResult.Success -> {
                    onGoToHome()
                }

                is AuthenticationResult.Failure -> {
                    onFailure()
                }
            }
        }
    }
}