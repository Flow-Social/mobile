package me.floow.chats.ui.chat.components.replyable

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

internal class CustomReplyAnchoredDraggableState(
	val initialValue: Float,
	val replyOffset: Float
) {
	var currentValue by mutableFloatStateOf(initialValue)
	var isDragged by mutableStateOf(false)
	var isAnimated by mutableStateOf(false)

	suspend fun animateTo(
		targetOffset: Float,
		animationVelocity: Float,
		snapAnimationSpec: AnimationSpec<Float>
	) {
		animate(
			initialValue = currentValue,
			targetValue = targetOffset,
			initialVelocity = animationVelocity,
			animationSpec = snapAnimationSpec
		) { value, _ ->
			currentValue = value
		}
	}

	fun requireOffset(): Float {
		return currentValue
	}
}