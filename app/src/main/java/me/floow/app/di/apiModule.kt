package me.floow.app.di

import me.floow.api.AuthApiImpl
import me.floow.auth.GoogleOAuthImpl
import me.floow.domain.api.AuthApi
import me.floow.domain.auth.GoogleOAuth
import org.koin.dsl.module

val apiModule = module {
    factory<AuthApi> { AuthApiImpl(get()) }
    factory<GoogleOAuth> { GoogleOAuthImpl(get(), get()) }
}
