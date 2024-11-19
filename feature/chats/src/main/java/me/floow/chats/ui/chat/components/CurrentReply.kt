package me.floow.chats.ui.chat.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.floow.uikit.theme.LocalTypography
import me.flowme.chats.R

@Composable
fun CurrentReply(
	userNameToReply: String,
	replyMessageText: String,
	onClose: () -> Unit,
	onClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier
			.clickable { onClick() }
			.padding(
				horizontal = 16.dp,
				vertical = 8.dp
			)
	) {
		Column(
			modifier = Modifier
				.weight(1f)
		) {
			Text(
				text = "${stringResource(R.string.in_reply_to)} $userNameToReply",
				style = LocalTypography.current.bodyMedium,
				overflow = TextOverflow.Ellipsis,
				fontWeight = FontWeight.Bold,
			)

			Text(
				text = replyMessageText,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				style = LocalTypography.current.captionMedium,
				color = Color.Gray
			)
		}

		IconButton(
			onClick = onClose
		) {
			Icon(
				painter = painterResource(R.drawable.current_reply_close_icon),
				contentDescription = null
			)
		}
	}
}

@Preview
@Composable
fun CurrentReplyPreview() {
	CurrentReply(
		userNameToReply = "Bogdan",
		replyMessageText = "Компот не квасят, а культивируют. даун",
		onClose = {},
		onClick = {},
		modifier = Modifier
			.fillMaxWidth()
	)
}