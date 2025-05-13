package me.floow.api.util

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.HttpTimeout

class HttpClientProvider {
	private val lazyClient: HttpClient by lazy {
		HttpClient(CIO) {
			install(HttpTimeout)
		}
	}

	fun getClient(): HttpClient {
		return lazyClient
	}
}