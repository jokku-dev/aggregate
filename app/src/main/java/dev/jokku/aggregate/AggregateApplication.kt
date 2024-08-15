package dev.jokku.aggregate

import android.app.Application
import dev.jokku.aggregate.data.sync.Sync
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AggregateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Sync.initialize(this)
    }
}