package me.floow.profile.uilogic

import android.net.Uri

data class ProfileSubscribers(
	val firstAvatar: Uri? = null,
	val secondAvatar: Uri? = null,
	val subscribersCount: Int
)
