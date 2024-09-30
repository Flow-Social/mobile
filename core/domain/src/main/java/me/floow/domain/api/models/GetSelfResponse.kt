package me.floow.domain.api.models

sealed interface GetSelfResponse {
	data class Success(
		val id: Long,
		val name: String?,
		val email: String?,
		val avatarUrl: String?,
		val biography: String?,
	) : GetSelfResponse

	data object Error : GetSelfResponse
}
