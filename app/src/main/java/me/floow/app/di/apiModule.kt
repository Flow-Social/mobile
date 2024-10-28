package me.floow.app.di

import me.floow.api.AuthApiImpl
import me.floow.api.ProfileApiImpl
import me.floow.api.util.HttpClientProvider
import me.floow.auth.GoogleOAuthImpl
import me.floow.domain.api.AuthApi
import me.floow.domain.api.ProfileApi
import me.floow.domain.auth.GoogleOAuth
import org.koin.dsl.module

val apiModule = module {
    single<HttpClientProvider> { HttpClientProvider() }
    factory<ProfileApi> { ProfileApiImpl(get(), get(), get()) }
    factory<AuthApi> { AuthApiImpl(get(), get(), get()) }
    factory<GoogleOAuth> { GoogleOAuthImpl(get(), get()) }
}
