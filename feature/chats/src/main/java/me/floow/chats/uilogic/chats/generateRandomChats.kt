package me.floow.chats.uilogic.chats

import android.net.Uri
import me.floow.domain.values.ProfileName
import java.time.LocalDateTime
import kotlin.random.Random

fun generateRandomChats(n: Int): List<Chat> {
	val firstNames = listOf(
		"John",
		"Jane",
		"Alice",
		"Bob",
		"Charlie",
		"Demn",
		"Finsi",
		"Mixno",
		"Max",
		"Vlad",
		"Andrew",
		"Саша",
		"котик"
	)
	val messages = listOf(
		"Hello!",
		"How are you?",
		"See you later!",
		"Good morning!",
		"Have a nice day!",
		"Смотри какие пельмени!",
		"ИМБИЩЕЕЕЕ!!!",
		"На видео Threads, написанный на Compose, а не приложение фейсбука",
		"Try out the fastest crypto-to-crypto \uD83D\uDD01 Swaps in Telegram and share the \$10,000 prize fund in our contest for all users! Learn more ›"
	)

	val chats = mutableListOf<Chat>()

	for (i in 1..n) {
		val profileName = ProfileName.create(
			value = firstNames.random(),
		)
		val lastMessageText = messages.random()
		val lastMessageDateTime = LocalDateTime.now()
			.minusDays(Random.nextLong(0, 30))
			.minusHours(Random.nextLong(0, 24))
			.minusMinutes(Random.nextLong(0, 60))
			.minusSeconds(Random.nextLong(0, 60))
		val isOnline = Random.nextBoolean()
		val hasMention = Random.nextBoolean()
		val chatMuted = Random.nextBoolean()
		val avatarUrl = if (Random.nextBoolean()) Uri.parse("https://example.com/avatar") else null
		val attachedMediaUrl =
			if (Random.nextBoolean()) Uri.parse("https://example.com/media") else null
		val lastSentMessageState =
			if (Random.nextBoolean()) LastSentMessageState.entries.toTypedArray().random() else null

		val chat = Chat(
			id = Random.nextLong(1L, 100L),
			name = profileName,
			lastMessageText = lastMessageText,
			lastMessageDateTime = lastMessageDateTime,
			isOnline = isOnline,
			hasMention = hasMention,
			chatMuted = chatMuted,
			avatarUrl = avatarUrl,
			attachedMediaUrl = attachedMediaUrl,
			lastSentMessageState = lastSentMessageState
		)

		chats.add(chat)
	}

	return chats
}