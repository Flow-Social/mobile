package me.floow.app

import android.util.Log
import me.floow.domain.utils.Logger

class LoggerImpl : Logger {
	override fun d(tag: String?, message: String) {
		Log.d(tag, message)
	}
}