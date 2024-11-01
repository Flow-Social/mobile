package me.floow.api.util.extensions

import io.ktor.client.request.HttpRequest
import me.floow.domain.utils.Logger

fun Logger.logKtorRequest(tag: String, request: HttpRequest) {
	this.d(tag, "${request.method.value} ${request.url}")
	this.d(tag, "Headers: ${request.headers}")
}