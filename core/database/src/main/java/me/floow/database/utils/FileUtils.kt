package me.floow.database.utils

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object FileUtils {
    suspend fun getBytesFromUri(uri: Uri, context: Context): ByteArray =
        withContext(Dispatchers.IO) {
            context.contentResolver.openInputStream(uri)
                ?.use { inputStream -> inputStream.readBytes() } ?: byteArrayOf()
        }
}