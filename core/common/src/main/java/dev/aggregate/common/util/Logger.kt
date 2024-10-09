package dev.aggregate.common.util

import android.util.Log

interface Logger {
    fun d(
        tag: String,
        message: String
    )

    fun e(
        tag: String,
        message: String
    )
}

@Suppress("ktlint:standard:function-naming")
fun AndroidLogcatLogger(): Logger =
    object : Logger {
        override fun d(
            tag: String,
            message: String
        ) {
            Log.d(tag, message)
        }

        override fun e(
            tag: String,
            message: String
        ) {
            Log.e(tag, message)
        }
    }
