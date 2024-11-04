package me.floow.profile.uilogic.profile

import android.net.Uri

data class ProfileSubscribers(
	val firstAvatar: Uri? = null,
	val secondAvatar: Uri? = null,
	val subscribersCount: Int
)
