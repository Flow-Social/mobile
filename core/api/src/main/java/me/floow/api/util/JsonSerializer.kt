package me.floow.api.util

import kotlinx.serialization.json.Json

val JsonSerializer = Json {
    ignoreUnknownKeys = true
}