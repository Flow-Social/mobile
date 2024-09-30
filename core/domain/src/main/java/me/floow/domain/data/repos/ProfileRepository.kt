package me.floow.domain.data.repos

import me.floow.domain.models.SelfProfile

interface ProfileRepository {
	fun getSelfData(): SelfProfile
}