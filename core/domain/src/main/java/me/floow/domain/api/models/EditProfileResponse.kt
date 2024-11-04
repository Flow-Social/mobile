package me.floow.domain.api.models

enum class EditProfileResponseStatus {
	SUCCESS,
	ERROR
}

data class EditProfileResponse(
	val status: EditProfileResponseStatus,
)
