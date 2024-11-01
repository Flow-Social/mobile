package me.floow.profile.uilogic.profile

import android.net.Uri

sealed interface ProfileScreenState {
	data object Loading : ProfileScreenState

	data object Error : ProfileScreenState

	data class Success(
		val shortUsername: String?,
		val avatarUri: Uri?,
		val displayName: String?,
		val description: String?,
		val subscribers: ProfileSubscribers,
	) : ProfileScreenState
}