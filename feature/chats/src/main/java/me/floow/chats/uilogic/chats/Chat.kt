package me.floow.chats.uilogic.chats

import android.net.Uri
import me.floow.domain.values.ProfileName
import java.time.LocalDateTime

enum class LastSentMessageState {
	Sent,
	Read,
}

data class Chat(
	val id: Long,
	val name: ProfileName,
	val lastMessageText: String,
	val lastMessageDateTime: LocalDateTime,
	val isOnline: Boolean,
	val hasMention: Boolean,
	val chatMuted: Boolean,
	val avatarUrl: Uri?,
	val attachedMediaUrl: Uri?,
	val lastSentMessageState: LastSentMessageState?
)