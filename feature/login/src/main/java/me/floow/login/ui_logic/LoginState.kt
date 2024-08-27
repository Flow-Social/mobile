package me.floow.login.ui_logic

interface LoginState {
    data object Idle : LoginState

    data object Loading : LoginState
}