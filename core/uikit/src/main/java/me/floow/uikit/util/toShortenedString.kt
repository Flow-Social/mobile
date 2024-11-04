package me.floow.uikit.util

import java.util.*

fun Int.toShortenedString(locale: Locale): String {
    return when {
        this >= 1_000_000 -> String.format(locale, "%.1fM", this / 1_000_000.0)
        this >= 1_000 -> String.format(locale, "%.1fk", this / 1_000.0)
        else -> this.toString()
    }
}
