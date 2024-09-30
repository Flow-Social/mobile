package me.floow.app.di

import me.floow.data.repos.ProfileRepositoryImpl
import me.floow.domain.data.repos.ProfileRepository
import org.koin.dsl.module

val dataModule = module {
	single<ProfileRepository> { ProfileRepositoryImpl(get()) }
}