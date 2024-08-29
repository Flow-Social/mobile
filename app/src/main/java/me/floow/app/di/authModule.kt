package me.floow.app.di

import me.floow.auth.AuthenticationManagerImpl
import me.floow.domain.auth.AuthenticationManager
import org.koin.dsl.module

val authModule = module {
    single<AuthenticationManager> { AuthenticationManagerImpl(get(), get(), get()) }
}