package me.floow.chats

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import me.floow.chats.ui.chat.ChatScreen
import me.floow.chats.uilogic.chat.ChatScreenViewModel
import me.floow.uikit.util.SetNavigationBarColor

data class ChatRouteInitialData(
	val chatInterlocutorId: Long,
	val chatInterlocutorName: String,
	val chatInterlocutorAvatarUrl: Uri?
)

@Composable
fun ChatRoute(
	initialData: ChatRouteInitialData,
	vm: ChatScreenViewModel,
	modifier: Modifier = Modifier
) {
	val state by vm.state.collectAsState()
	val context = LocalContext.current

	LaunchedEffect(Unit) {
		vm.setInitialData(
			initialData.chatInterlocutorId,
			initialData.chatInterlocutorName,
			initialData.chatInterlocutorAvatarUrl,
		)

		vm.loadData()
	}

	ChatScreen(
		onProfileClick = {},
		onTopBarDropdownClick = {},
		onChatBubbleClick = {},
		onReply = vm::addCurrentReply,
		onReplyClick = {},
		onCurrentReplyClose = vm::closeCurrentReply,
		onCurrentReplyClick = { showTodoToast(context) },
		onMessageInputFieldValueChange = vm::updateMessageInputField,
		onEmojiPickerClick = { showTodoToast(context) },
		onSendClick = vm::sendMessage,
		state = state,
		modifier = modifier,
	)

	SetNavigationBarColor(
		MaterialTheme.colorScheme.background
	)
}

private fun showTodoToast(context: Context) {
	Toast.makeText(context, "feature currently unavailable", Toast.LENGTH_SHORT).show()
}