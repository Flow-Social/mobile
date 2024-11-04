package me.floow.app.di

import me.floow.api.util.ApiConfig
import me.floow.app.LoggerImpl
import me.floow.app.di.configs.apiConfig
import me.floow.app.di.configs.googleOAuthInfoConfig
import me.floow.auth.models.GoogleOAuthInfo
import me.floow.domain.utils.Logger
import org.koin.dsl.module

val appModule = module {
    single<GoogleOAuthInfo> { googleOAuthInfoConfig }
    single<ApiConfig> { apiConfig }
    single<Logger> { LoggerImpl() }
}