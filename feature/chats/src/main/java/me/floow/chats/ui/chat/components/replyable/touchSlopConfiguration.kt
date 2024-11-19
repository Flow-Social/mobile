package me.floow.chats.ui.chat.components.replyable

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.ViewConfiguration

@Composable
internal fun touchSlopConfiguration(current: ViewConfiguration) =
	LocalViewConfiguration provides object : ViewConfiguration by current {
		override val touchSlop: Float
			get() = current.touchSlop * 2f
	}