package me.floow.profile.di

import me.floow.profile.uilogic.profile.ProfileScreenViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf

val profileModule = module {
	viewModelOf(::ProfileScreenViewModel)
}