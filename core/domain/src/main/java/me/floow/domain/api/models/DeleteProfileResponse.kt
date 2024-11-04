package me.floow.domain.api.models

enum class DeleteProfileResponseStatus {
	SUCCESS,
	ERROR
}

data class DeleteProfileResponse(val status: DeleteProfileResponseStatus)
