package me.floow.domain.api.models

sealed interface GetSelfResponse {
	data class Success(
		val name: String?,
		val username: String?,
		val avatarUrl: String?,
		val biography: String?,
	) : GetSelfResponse

	data object Error : GetSelfResponse
}
