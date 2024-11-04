package me.floow.app.di

import me.floow.database.sharedpref.ProfileCacheProviderImpl
import me.floow.domain.cache.ProfileCacheProvider
import org.koin.dsl.module

val databaseModule = module {
	single<ProfileCacheProvider> { ProfileCacheProviderImpl(get()) }
}