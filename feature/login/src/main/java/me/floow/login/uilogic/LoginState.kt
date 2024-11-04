package me.floow.login.uilogic

interface LoginState {
    data object Idle : LoginState

    data object Loading : LoginState
}