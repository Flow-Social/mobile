package me.floow.chats.uilogic.chat

import java.time.LocalDateTime
import kotlin.random.Random

fun generateChatMessages(): List<ChatMessage> {
    val messageTexts = listOf(
        "Hello!", "How are you?", "ну молодец что", "Good morning!", "Good evening!",
        "See you later!", "Thank you!", "You're welcome!", "Have a nice day!",
        "Если нам снятся люди, предметы. Значит наши мозг знает как все это выглядит, значит он может это нарисовать не только в мозгу, но и на бумаге. Способный, просто не хочешь",
        "Наш взгляд - человечен, а вот мир - бесчеловечен. Поэтому нужно нашим человеческим взглядом, обратить бесчеловечный мир в человечный",
        "КАПЕЦ ТАМ СЕРГЕЙ ВОЛКОВ\nНИЦШЕ ЧИТАЛ",
        "ИМБИЩЕЕЕЕ!!!",
        "эээ",
        "не",
        "потом",
        "да",
        "ок",
        "У меня и в тг видно бывает, но пропадает)) это скорей всего от фронталки съехало, но зачем там такое хз",
        "Это в Яндекс музыке видно",
        "Утечка слайдов из Google раскрыла некоторые из будущих планов компании в отношении чипсетов Tensor, в частности Tensor G6, и Google явно сосредоточилась на решении проблем с нагревом и эффективностью.",
        "Для этого Tensor G6 будет физически меньше G5 (121 мм² против 105 мм²), а также будут удалены различные компоненты. Например, GPU откажется от поддержки трассировки лучей после того, как она появилась на поколение раньше. Среди других примеров - удаление ядер и уменьшение кэша по всему чипу. Разумеется, ничего из этого пока не утверждено.",
        "Хз, кто-то из вас вообще предлагал медиа в s3 хранить"
    )

    val random = Random
    val chatMessages = mutableListOf<ChatMessage>()

    for (i in 0..100) {
        val id = i.toLong()
        val messageText = messageTexts[random.nextInt(messageTexts.size)]
        val dateTime = LocalDateTime.now().minusDays(random.nextLong(30)).withNano(0)

        when (i % 4) {
            0 -> {
                val replyMessageId = (i - 1).toLong()
                val replyMessageText = messageTexts[random.nextInt(messageTexts.size)]
                chatMessages.add(ReplyInMessage(id, replyMessageId, replyMessageText, messageText, dateTime))
            }
            1 -> {
                val replyMessageId = (i - 1).toLong()
                val replyMessageText = messageTexts[random.nextInt(messageTexts.size)]
                chatMessages.add(ReplyOutMessage(id, messageText, dateTime, replyMessageId, replyMessageText))
            }
            2 -> {
                chatMessages.add(PrimaryInMessage(id, messageText, dateTime))
            }
            3 -> {
                chatMessages.add(PrimaryOutMessage(id, messageText, dateTime))
            }
        }
    }

    return chatMessages
}