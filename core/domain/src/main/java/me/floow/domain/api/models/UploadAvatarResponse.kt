package me.floow.domain.api.models

enum class UploadAvatarResponseStatus {
    SUCCESS,
    ERROR
}

data class UploadAvatarResponse(
    val status: UploadAvatarResponseStatus,
)
