package me.floow.api.util

import io.ktor.util.network.*
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.io.EOFException
import kotlinx.io.IOException

suspend fun <T> safeApiCall(errorResponse: T, apiCall: suspend () -> T): T {
	return try {
		apiCall()
	} catch (ex: UnresolvedAddressException) {
		errorResponse
	} catch (ex: TimeoutCancellationException) {
		errorResponse
	} catch (ex: EOFException) {
		errorResponse
	} catch (ex: IOException) {
		errorResponse
	}
}
