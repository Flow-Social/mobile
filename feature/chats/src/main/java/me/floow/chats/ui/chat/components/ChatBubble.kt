package me.floow.chats.ui.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.chats.uilogic.chat.ChatMessage
import me.floow.chats.uilogic.chat.ChatReplyMessage
import me.floow.chats.uilogic.chat.PrimaryInMessage
import me.floow.chats.uilogic.chat.PrimaryOutMessage
import me.floow.chats.uilogic.chat.ReplyInMessage
import me.floow.chats.uilogic.chat.ReplyOutMessage
import me.floow.uikit.R
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.util.ComponentPreviewBox
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class ChatBubbleColors(
	val backgroundColor: Color,
	val borderColor: Color,
	val textColor: Color,
	val timeColor: Color,
	val replyColor: Color,
) {
	companion object {
		val Default: ChatBubbleColors
			@Composable get() = ChatBubbleColors(
				backgroundColor = MaterialTheme.colorScheme.inversePrimary,
				borderColor = Color(0xFFBEBEBE),
				textColor = MaterialTheme.colorScheme.onBackground,
				timeColor = MaterialTheme.colorScheme.onBackground,
				replyColor = Color(0xFFBEBEBE),
			)

		val Outlined: ChatBubbleColors
			@Composable get() = ChatBubbleColors(
				backgroundColor = MaterialTheme.colorScheme.background,
				borderColor = Color(0xFFBEBEBE),
				textColor = MaterialTheme.colorScheme.onBackground,
				timeColor = Color(0xFFBEBEBE),
				replyColor = Color(0xFFBEBEBE),
			)
	}
}

@Composable
fun ChatBubble(
	chatMessage: ChatMessage,
	onClick: (ChatMessage) -> Unit,
	onReplyClick: (ChatMessage) -> Unit,
	modifier: Modifier = Modifier
) {
	val isReply: Boolean = chatMessage is ChatReplyMessage
	val isOut: Boolean = chatMessage is PrimaryOutMessage || chatMessage is ReplyOutMessage

	val colors = if (!isOut) ChatBubbleColors.Outlined else ChatBubbleColors.Default

	Column(
		horizontalAlignment = if (isOut) Alignment.End else Alignment.Start,
		modifier = modifier,
	) {
		Column(
			modifier = Modifier
				.clip(RoundedCornerShape(20.dp))
				.clickable { onClick(chatMessage) }
				.background(colors.backgroundColor)
				.addBorderIfIn(isOut, colors)
				.padding(vertical = 8.dp, horizontal = 10.dp)
				.widthIn(74.dp, 324.dp)
				.width(IntrinsicSize.Max)
		) {
			Text(
				text = chatMessage.messageText,
				color = colors.textColor,
				style = LocalTypography.current.bodyMedium,
				modifier = Modifier
			)

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.End,
			) {
				Text(
					text = chatMessage.dateTime.format(DateTimeFormatter.ofPattern("hh:mm")),
					color = colors.timeColor,
					style = LocalTypography.current.labelMedium,
					textAlign = TextAlign.End,
				)
			}
		}

		if (chatMessage is ChatReplyMessage) {
			Spacer(Modifier.height(3.dp))

			Row(
				horizontalArrangement = if (isOut) Arrangement.End else Arrangement.Start,
				modifier = Modifier
					.width(324.dp)
			) {
				ReplyContent(
					replyMessageText = chatMessage.replyMessageText,
					color = colors.replyColor,
					modifier = Modifier
						.height(25.dp)
						.clickable { onReplyClick(chatMessage) }
				)
			}
		}
	}
}

private fun Modifier.addBorderIfIn(out: Boolean, colors: ChatBubbleColors): Modifier {
	return if (out) this
	else this.then(Modifier.border(1.dp, colors.borderColor, RoundedCornerShape(20.dp)))
}

@Composable
private fun ReplyContent(
	replyMessageText: String,
	color: Color,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			painter = painterResource(R.drawable.reply_out_icon),
			contentDescription = null,
			tint = color,
		)

		Spacer(Modifier.width(6.dp))

		Text(
			text = replyMessageText,
			color = color,
			style = LocalTypography.current.bodyMedium,
			maxLines = 1,
			overflow = TextOverflow.Ellipsis,
			modifier = Modifier
		)
	}
}

@Preview
@Composable
fun ChatBubblePreview_OutReply() {
	val mockMessage = ReplyOutMessage(
		id = 1L,
		messageText = "This is a reply out message",
		dateTime = LocalDateTime.now(),
		replyMessageId = 2L,
		replyMessageText = "This is the message being replied to"
	)

	ComponentPreviewBox(Modifier.fillMaxWidth()) {
		ChatBubble(
			chatMessage = mockMessage,
			onClick = {},
			onReplyClick = {}
		)
	}
}

@Preview
@Composable
fun ChatBubblePreview_InReply() {
	val mockMessage = ReplyInMessage(
		id = 1L,
		replyMessageId = 2L,
		replyMessageText = "This is the message being replied to",
		messageText = "This is a reply in message",
		dateTime = LocalDateTime.now()
	)

	ComponentPreviewBox(Modifier.fillMaxWidth()) {
		ChatBubble(
			chatMessage = mockMessage,
			onClick = {},
			onReplyClick = {}
		)
	}
}

@Preview
@Composable
fun ChatBubblePreview_OutPrimary() {
	val mockMessage = PrimaryOutMessage(
		id = 1L,
		messageText = "This is a primary out message",
		dateTime = LocalDateTime.now()
	)

	ComponentPreviewBox(Modifier.fillMaxWidth()) {
		ChatBubble(
			chatMessage = mockMessage,
			onClick = {},
			onReplyClick = {}
		)
	}
}

@Preview
@Composable
fun ChatBubblePreview_InPrimary() {
	val mockMessage = PrimaryInMessage(
		id = 1L,
		messageText = "This is a primary in message. By the way, This is a primary in message. Lorem ipsum dolor sit amet.. Yeahhh",
		dateTime = LocalDateTime.now()
	)

	ComponentPreviewBox(Modifier.fillMaxWidth()) {
		ChatBubble(
			chatMessage = mockMessage,
			onClick = {},
			onReplyClick = {}
		)
	}
}