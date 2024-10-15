package me.floow.login.di

import org.koin.dsl.module
import me.floow.login.uilogic.LoginViewModel
import me.floow.login.uilogic.CreateProfileViewModel
import org.koin.core.module.dsl.viewModelOf

val loginModule = module {
    viewModelOf(::LoginViewModel)

    viewModelOf(::CreateProfileViewModel)
}