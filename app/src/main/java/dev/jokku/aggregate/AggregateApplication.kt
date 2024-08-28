package dev.jokku.aggregate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.jokku.sync.initializers.Sync

@HiltAndroidApp
class AggregateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Sync.initialize(this)
    }
}