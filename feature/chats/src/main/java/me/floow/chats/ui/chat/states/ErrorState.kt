package me.floow.chats.ui.chat.states

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ErrorState(modifier: Modifier = Modifier) {
	Box(
		modifier = modifier,
		contentAlignment = Alignment.Center
	) {
		Text(
			text = "error occured"
		)
	}
}