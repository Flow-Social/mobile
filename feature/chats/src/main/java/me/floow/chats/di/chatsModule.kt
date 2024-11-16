package me.floow.chats.di

import me.floow.chats.uilogic.chats.ChatsScreenViewModel
import me.floow.chats.uilogic.chat.ChatScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chatsModule = module {
	viewModelOf(::ChatsScreenViewModel)
	viewModelOf(::ChatScreenViewModel)
}