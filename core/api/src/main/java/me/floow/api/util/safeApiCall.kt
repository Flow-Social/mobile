package me.floow.api.util

import io.ktor.util.network.*
import io.ktor.utils.io.core.EOFException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.TimeoutCancellationException

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