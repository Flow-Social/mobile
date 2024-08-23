package com.flowme.login.ui_logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flowme.domain.auth.AuthenticationManager
import com.flowme.domain.auth.models.AuthState
import com.flowme.domain.auth.models.AuthenticationResult
import com.flowme.domain.auth.models.GoogleOAuthResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authenticationManager: AuthenticationManager
) : ViewModel() {
    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun initialize(
        onGoToHome: () -> Unit,
        onFailure: () -> Unit,
    ) {
        viewModelScope.launch {
            authenticationManager.authenticationStateFlow.collect { state ->
                when (state) {
                    is AuthState.HasResult -> {
                        handleAuthStateResult(
                            state.authenticationResult,
                            onGoToHome,
                            onFailure
                        )
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun handleAuthStateResult(
        result: AuthenticationResult,
        onGoToHome: () -> Unit,
        onFailure: () -> Unit,
    ) {
        when (result) {
            is AuthenticationResult.Success -> onGoToHome()

            is AuthenticationResult.Failure -> onFailure()
        }
        _loginState.update { LoginState.Idle }
    }

    fun loginWithGoogle() {
        viewModelScope.launch {
            _loginState.update { LoginState.Loading }

            authenticationManager.startGoogleAuthentication()
        }
    }
}