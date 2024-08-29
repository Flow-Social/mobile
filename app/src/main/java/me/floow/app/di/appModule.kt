package me.floow.app.di

import me.floow.api.AuthApiConfig
import me.floow.app.di.configs.authApiConfig
import me.floow.app.di.configs.googleOAuthInfoConfig
import me.floow.auth.models.GoogleOAuthInfo
import org.koin.dsl.module

val appModule = module {
    single<GoogleOAuthInfo> { googleOAuthInfoConfig }
    single<AuthApiConfig> { authApiConfig }
}