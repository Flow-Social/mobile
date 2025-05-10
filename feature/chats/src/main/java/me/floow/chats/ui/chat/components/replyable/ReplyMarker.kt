package me.floow.chats.ui.chat.components.replyable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.floow.uikit.R

@Composable
internal fun ReplyMarker(
	modifier: Modifier = Modifier
) {
	Icon(
		painter = painterResource(R.drawable.reply_icon),
		contentDescription = null,
		tint = Color.Black,
		modifier = modifier
			.clip(RoundedCornerShape(8.dp))
			.background(MaterialTheme.colorScheme.secondaryContainer)
			.padding(
				horizontal = 8.dp,
				vertical = 4.dp
			)
	)
}