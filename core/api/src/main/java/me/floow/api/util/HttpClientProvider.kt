package me.floow.api.util

import io.ktor.client.*
import io.ktor.client.engine.cio.*

class HttpClientProvider {
	private val lazyClient: HttpClient by lazy {
		HttpClient(CIO)
	}

	fun getClient(): HttpClient {
		return lazyClient
	}
}