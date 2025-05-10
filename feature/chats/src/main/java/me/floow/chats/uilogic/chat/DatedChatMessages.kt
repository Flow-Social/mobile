package me.floow.chats.uilogic.chat

import java.time.LocalDate

data class DatedChatMessages(
	val datetime: LocalDate,
	val messages: List<ChatMessage>
)