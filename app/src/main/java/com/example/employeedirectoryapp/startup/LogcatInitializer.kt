package com.example.employeedirectoryapp.startup

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import logcat.AndroidLogcatLogger
import logcat.LogPriority
import logcat.logcat

class LogcatInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        AndroidLogcatLogger.installOnDebuggableApp(
            context as Application,
            minPriority = LogPriority.VERBOSE
        )
        logcat { "LogcatInitializer is initialized" }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}