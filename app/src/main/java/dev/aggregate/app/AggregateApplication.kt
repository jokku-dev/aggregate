package dev.aggregate.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.aggregate.sync.initializers.Sync

@HiltAndroidApp
class AggregateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Sync.initialize(this)
    }
}