package me.floow.chats.di

import me.floow.chats.uilogic.chats.ChatsScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chatsModule = module {
	viewModelOf(::ChatsScreenViewModel)
}