package me.floow.app.di

import me.floow.auth.AuthenticationManagerImpl
import me.floow.domain.auth.AuthenticationManager
import me.floow.mock.auth.MockAuthenticationManager
import org.koin.dsl.module

val authModule = module {
    single<AuthenticationManager> { AuthenticationManagerImpl(get(), get(), get(), get()) }
}

val mockAuthModule = module {
    single<AuthenticationManager> { MockAuthenticationManager() }
}