package me.floow.database.sharedpref

import android.content.Context
import me.floow.domain.cache.ProfileCacheProvider
import me.floow.domain.models.SelfProfile
import me.floow.domain.values.ProfileDescription
import me.floow.domain.values.ProfileName

class ProfileCacheProviderImpl(
	context: Context
) : ProfileCacheProvider {
	companion object {
		private const val PROFILE_CACHE_NAME = "profile_cache"
		private const val PROFILE_ID = "profile_id"
		private const val PROFILE_NAME = "profile_name"
		private const val PROFILE_BIO = "profile_bio"
		private const val PROFILE_AVATAR_URL = "profile_avatar_url"
		private const val PROFILE_EMAIL = "profile_email"
	}

	private val sharedPreferences =
		context.getSharedPreferences(PROFILE_CACHE_NAME, Context.MODE_PRIVATE)

	override fun getSelfProfile(): SelfProfile? {
		val id = sharedPreferences.getLong(PROFILE_ID, -1L)
		val name = sharedPreferences.getString(PROFILE_NAME, null)
		val bio = sharedPreferences.getString(PROFILE_BIO, null)
		val avatarUrl = sharedPreferences.getString(PROFILE_AVATAR_URL, null)
		val email = sharedPreferences.getString(PROFILE_EMAIL, null)

		if (id == -1L) return null

		return SelfProfile(
			id = id,
			name = name?.let { ProfileName(it) },
			email = email,
			avatarUrl = avatarUrl,
			description = bio?.let { ProfileDescription(it) }
		)
	}

	override fun updateSelfProfile(profile: SelfProfile) {
		sharedPreferences.edit().apply {
			putLong(PROFILE_ID, profile.id)
			putString(PROFILE_NAME, profile.name?.value)
			putString(PROFILE_BIO, profile.description?.value)
			putString(PROFILE_AVATAR_URL, profile.avatarUrl)
			putString(PROFILE_EMAIL, profile.email)

			apply()
		}
	}

	override fun clearSelfProfile() {
		updateSelfProfile(
			SelfProfile(
				id = -1L,
				name = null,
				description = null,
				avatarUrl = null,
				email = null,
			)
		)
	}
}