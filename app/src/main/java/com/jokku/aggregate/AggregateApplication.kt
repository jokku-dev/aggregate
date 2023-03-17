package com.jokku.aggregate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AggregateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}