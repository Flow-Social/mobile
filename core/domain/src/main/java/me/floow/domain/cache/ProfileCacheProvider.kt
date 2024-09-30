package me.floow.domain.cache

import me.floow.domain.models.SelfProfile

interface ProfileCacheProvider {
	fun getSelfProfile(): SelfProfile?

	fun updateSelfProfile(profile: SelfProfile)

	fun clearSelfProfile()
}