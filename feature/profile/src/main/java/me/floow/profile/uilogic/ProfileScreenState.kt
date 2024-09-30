package me.floow.profile.uilogic

import android.net.Uri

sealed interface ProfileScreenState {
	data object Loading : ProfileScreenState

	data object Error : ProfileScreenState

	data class Success(
		val shortUsername: String,
		val avatarUrl: Uri,
		val displayName: String,
		val description: String,
	) : ProfileScreenState
}