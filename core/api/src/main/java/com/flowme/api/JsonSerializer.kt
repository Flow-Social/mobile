package com.flowme.api

import kotlinx.serialization.json.Json

val JsonSerializer = Json {
    ignoreUnknownKeys = true
}