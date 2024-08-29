package me.floow.login.uilogic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import me.floow.domain.auth.AuthenticationManager
import me.floow.domain.auth.models.AuthState
import me.floow.domain.auth.models.AuthenticationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
                        _loginState.value = LoginState.Loading

                        handleAuthStateResult(
                            state.authenticationResult,
                            onGoToHome,
                            onFailure
                        )
                    }

                    is AuthState.HandlingOAuth -> {
                        _loginState.value = LoginState.Loading
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
            is AuthenticationResult.Success -> {
                onGoToHome()
                _loginState.value = LoginState.Idle
            }

            is AuthenticationResult.Failure -> {
                onFailure()
                _loginState.value = LoginState.Idle
            }
        }
    }

    fun loginWithGoogle() {
        viewModelScope.launch {
            authenticationManager.startGoogleAuthentication()
        }
    }
}