package me.floow.domain.api

import me.floow.domain.api.models.DeleteProfileResponse
import me.floow.domain.api.models.EditProfileData
import me.floow.domain.api.models.EditProfileResponse
import me.floow.domain.api.models.GetSelfResponse
import me.floow.domain.api.models.UploadAvatarResponse

interface ProfileApi {
	suspend fun getSelf(): GetSelfResponse

	suspend fun edit(data: EditProfileData): EditProfileResponse

	suspend fun uploadAvatar(avatarBytes: ByteArray): UploadAvatarResponse

	suspend fun deleteProfile(): DeleteProfileResponse
}