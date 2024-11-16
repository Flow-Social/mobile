package me.floow.chats.uilogic.chat

import android.net.Uri

data class MessageFieldReply(
	val replyId: Long,
	val replyMessageText: String,
)

interface ChatScreenUiState {
	val chatInterlocutorId: Long
	val chatInterlocutorName: String
	val chatInterlocutorAvatarUrl: Uri?
	val messageFieldValue: String
	val messageFieldReply: MessageFieldReply?

	data class Loading(
		override val chatInterlocutorId: Long,
		override val chatInterlocutorAvatarUrl: Uri?,
		override val messageFieldValue: String,
		override val chatInterlocutorName: String,
		override val messageFieldReply: MessageFieldReply?,
	) : ChatScreenUiState

	data class Error(
		override val chatInterlocutorId: Long,
		override val chatInterlocutorAvatarUrl: Uri?,
		override val messageFieldValue: String,
		override val chatInterlocutorName: String,
		override val messageFieldReply: MessageFieldReply?,
	) : ChatScreenUiState

	data class NoMessages(
		override val chatInterlocutorId: Long,
		override val chatInterlocutorAvatarUrl: Uri?,
		override val messageFieldValue: String,
		override val chatInterlocutorName: String,
		override val messageFieldReply: MessageFieldReply?,
	) : ChatScreenUiState

	data class HasData(
		val messages: List<ChatMessage>,
		override val chatInterlocutorId: Long,
		override val chatInterlocutorAvatarUrl: Uri?,
		override val messageFieldValue: String,
		override val chatInterlocutorName: String,
		override val messageFieldReply: MessageFieldReply?,
	) : ChatScreenUiState
}