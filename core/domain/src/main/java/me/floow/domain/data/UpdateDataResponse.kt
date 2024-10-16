package me.floow.domain.data

enum class FailureError {
	Other
}

sealed interface UpdateDataResponse {
	data object Success : UpdateDataResponse

	data class Failure(
		val failureError: FailureError = FailureError.Other
	) : UpdateDataResponse
}