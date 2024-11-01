package me.floow.api.util.extensions

import io.ktor.client.request.*

fun HttpRequestBuilder.addAuthTokenHeader(authToken: String) {
	header("X-Authorization-Token", "$authToken")
}