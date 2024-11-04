package com.demn.usersearch.di

import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf
import com.demn.usersearch.uilogic.SearchUsersScreenViewModel

val usersearchModule = module {
	viewModelOf(::SearchUsersScreenViewModel)
}