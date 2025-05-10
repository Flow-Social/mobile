package me.floow.chats.ui.chat.states

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.floow.chats.ui.chat.components.DateSeparator
import me.floow.chats.ui.chat.components.replyable.ReplyableChatBubble
import me.floow.chats.uilogic.chat.ChatMessage
import me.floow.chats.uilogic.chat.ChatScreenUiState
import me.floow.chats.uilogic.chat.PrimaryOutMessage
import me.floow.chats.uilogic.chat.ReplyOutMessage
import me.flowme.chats.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HasDataState(
	state: ChatScreenUiState.HasData,
	onChatBubbleClick: (ChatMessage) -> Unit,
	onReply: (ChatMessage) -> Unit,
	onReplyClick: (ChatMessage) -> Unit,
	modifier: Modifier = Modifier
) {
	val lazyListState = rememberLazyListState()
	var dateSeparatorVisible by remember { mutableStateOf(false) }
	val coroutineScope = rememberCoroutineScope()
	val firstVisibleItemScrollOffset by remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
	var dateSeparatorJob by remember { mutableStateOf<Job?>(null) }

	LaunchedEffect(firstVisibleItemScrollOffset) {
		if (dateSeparatorJob != null) {
			dateSeparatorJob?.cancel()
		}

		dateSeparatorJob = coroutineScope.launch {
			dateSeparatorVisible = true

			delay(300L)

			dateSeparatorVisible = false
		}
	}

	val dateSeparatorModifier = Modifier
		.fillMaxWidth()
		.height(32.dp)

	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Box(
			Modifier
				.fillMaxWidth()
				.weight(1f)
		) {
			LazyColumn(
				state = lazyListState,
				modifier = Modifier
					.fillMaxWidth()
					.fillMaxHeight()
			) {
				state.messages.forEach { (date, messageList) ->
//				stickyHeader {
//					Spacer(Modifier.height(8.dp))
//
//					Row(
//						horizontalArrangement = Arrangement.Center,
//						modifier = dateSeparatorModifier,
//					) {
//						if (dateSeparatorVisible) {
//							DateSeparator(
//								text = getDateSeparatorText(date)
//							)
//						}
//					}
//
//					Spacer11
					//	 				(Modifier.height(8.dp))
//				}

					item {
						Spacer(Modifier.height(8.dp))

						Row(
							horizontalArrangement = Arrangement.Center,
							modifier = dateSeparatorModifier,
						) {
							DateSeparator(
								text = getDateSeparatorText(date)
							)
						}

						Spacer(Modifier.height(8.dp))
					}

					items(messageList) { message ->
						val isOut: Boolean =
							message is PrimaryOutMessage || message is ReplyOutMessage

						Row(
							modifier = Modifier
								.fillMaxWidth()
								.padding(horizontal = 14.dp),
							horizontalArrangement = if (isOut) Arrangement.End else Arrangement.Start
						) {
							ReplyableChatBubble(
								chatMessage = message,
								onClick = onChatBubbleClick,
								onReplyClick = onReplyClick,
								onReply = onReply,
								modifier = Modifier
									.fillMaxWidth()
							)
						}

						Spacer(Modifier.height(8.dp))
					}
				}
			}

			Column {
				Spacer(Modifier.height(8.dp))

				Row(
					horizontalArrangement = Arrangement.Center,
					modifier = dateSeparatorModifier,
				) {
					AnimatedVisibility (
						visible = dateSeparatorVisible,
						enter = fadeIn(),
						exit = fadeOut()
					) {
						DateSeparator(
							text = "Today"
						)
					}
				}

				Spacer(Modifier.height(8.dp))
			}
		}
	}
}

@Composable
fun getDateSeparatorText(date: LocalDate): String {
	return if (date == LocalDate.now()) {
		stringResource(R.string.today)
	} else {
		date.format(DateTimeFormatter.ofPattern("dd.MM.yy"))
	}
}
