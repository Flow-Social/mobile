package me.floow.chats.ui.chats.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.chats.uilogic.chats.Chat
import me.floow.chats.uilogic.chats.LastSentMessageState
import me.floow.domain.values.ProfileName
import me.floow.uikit.theme.LocalTypography
import me.floow.uikit.util.ComponentPreviewBox
import me.flowme.chats.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
internal fun ChatListItem(
	chat: Chat,
	onClick: (Chat) -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.clickable { onClick(chat) }
			.padding(horizontal = 14.dp, vertical = 8.dp)
			.height(62.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		AvatarBox(
			avatarUrl = chat.avatarUrl,
			isOnline = chat.isOnline,
			modifier = Modifier
		)

		Spacer(Modifier.width(12.dp))

		Column(
			modifier = Modifier
		) {
			Row(
				modifier = Modifier,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					text = chat.name.value,
					style = LocalTypography.current.titleMedium
				)

				if (chat.chatMuted) {
					Spacer(Modifier.width(4.dp))

					Icon(
						painter = painterResource(R.drawable.chat_muted_icon),
						contentDescription = null,
						tint = Color.Unspecified
					)
				}

				Spacer(Modifier.weight(1f))

				if (chat.lastSentMessageState != null) {
					when (chat.lastSentMessageState) {
						LastSentMessageState.Sent -> {
							// TODO
						}

						LastSentMessageState.Read -> {
							Icon(
								painter = painterResource(R.drawable.chat_read_icon),
								contentDescription = null,
								tint = Color.Unspecified
							)
						}
					}
				}

				Text(
					text = chat.lastMessageDateTime.format(DateTimeFormatter.ofPattern("HH:mm")),
					style = LocalTypography.current.labelMedium
				)
			}

			Spacer(Modifier.height(4.dp))

			Row(
				modifier = Modifier,
				verticalAlignment = Alignment.CenterVertically
			) {
				if (chat.attachedMediaUrl != null) {
					Box(
						Modifier
							.clip(RoundedCornerShape(4.dp))
							.background(Color.LightGray)
							.size(18.dp)
					)

					Spacer(Modifier.width(6.dp))
				}

				Text(
					text = chat.lastMessageText,
					style = LocalTypography.current.bodyMedium,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis,
					color = MaterialTheme.colorScheme.secondary
				)

				Spacer(Modifier.weight(1f))

				if (chat.hasMention) {
					Icon(
						painter = painterResource(R.drawable.chat_mention_icon),
						contentDescription = null,
						tint = Color.Unspecified
					)
				}
			}
		}
	}
}

@Composable
private fun AvatarBox(avatarUrl: Uri?, isOnline: Boolean, modifier: Modifier.Companion) {
	// todo isOnline handling

	Box(modifier = modifier) {
		Box(
			Modifier
				.size(54.dp)
				.clip(CircleShape)
				.background(Color.LightGray)
		)

		if (isOnline) {
			Box(
				Modifier
					.clip(CircleShape)
					.background(MaterialTheme.colorScheme.background)
					.padding(3.dp)
					.clip(CircleShape)
					.size(12.dp)
					.background(Color(0xFF5ACD30))
					.align(Alignment.BottomEnd)
			)
		}
	}
}

@Preview
@Composable
private fun ChatListItemPreview() {
	ComponentPreviewBox(Modifier.fillMaxSize()) {
		ChatListItem(
			chat = Chat(
				name = ProfileName("Demn"),
				lastMessageText = "Some message text idk",
				isOnline = true,
				lastMessageDateTime = LocalDateTime.now().minusHours(3),
				avatarUrl = null,
				attachedMediaUrl = null,
				chatMuted = true,
				hasMention = false,
				lastSentMessageState = LastSentMessageState.Read
			),
			onClick = {},
			modifier = Modifier.fillMaxWidth()
		)
	}
}