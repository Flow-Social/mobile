package me.floow.chatssearch.di

import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf
import me.floow.chatssearch.uilogic.SearchUsersScreenViewModel

val usersearchModule = module {
	viewModelOf(::SearchUsersScreenViewModel)
}