package me.floow.api.util.extensions

import io.ktor.http.HttpStatusCode
import me.floow.domain.utils.Logger

fun Logger.logFailureResponse(tag: String, status: HttpStatusCode, body: String) {
	this.d(tag, "Failure: $status $body")
}