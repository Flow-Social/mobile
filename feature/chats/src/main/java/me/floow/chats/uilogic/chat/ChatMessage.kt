package me.floow.chats.uilogic.chat

import java.time.LocalDateTime

sealed interface ChatMessage {
	val id: Long
	val messageText: String
	val dateTime: LocalDateTime
}

sealed interface ChatReplyMessage : ChatMessage {
	val replyMessageId: Long
	val replyMessageText: String
}

data class PrimaryOutMessage(
	override val id: Long,
	override val messageText: String,
	override val dateTime: LocalDateTime
) : ChatMessage

data class ReplyOutMessage(
	override val id: Long,
	override val messageText: String,
	override val dateTime: LocalDateTime,
	override val replyMessageId: Long,
	override val replyMessageText: String,
) : ChatReplyMessage

data class PrimaryInMessage(
	override val id: Long,
	override val messageText: String,
	override val dateTime: LocalDateTime
) : ChatMessage

data class ReplyInMessage(
	override val id: Long,
	override val replyMessageId: Long,
	override val replyMessageText: String,
	override val messageText: String,
	override val dateTime: LocalDateTime
) : ChatReplyMessage