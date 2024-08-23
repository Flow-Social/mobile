package com.flowme.login.ui_logic

interface LoginState {
    data object Idle : LoginState

    data object Loading : LoginState
}