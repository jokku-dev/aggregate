package com.jokku.aggregate.data.sync

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Constraints
import androidx.work.ForegroundInfo
import androidx.work.NetworkType
import com.jokku.aggregate.R

private const val SyncNotificationId = 0
private const val SyncNotificationChannelId = "SyncNotificationChannel"

val SyncConstraints
    get() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

//Foreground Service type is default so we don't need to use ForegroundInfo with type parameter
fun Context.syncForegroundInfo() = ForegroundInfo(
    //as far as we use only one channel, providing const id this way is enough
    SyncNotificationId,
    syncWorkNotification()
)
//Create notification channel if Android 8.0+ and build notification
private fun Context.syncWorkNotification(): Notification {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            SyncNotificationChannelId,
            getString(R.string.sync_notification),
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = getString(R.string.foreground_task_for_syncing_news)
        }

        val notificationManager: NotificationManager? =
            getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        // Creating the notification channel in an android 8.0+
        notificationManager?.createNotificationChannel(channel)
    }

    return NotificationCompat.Builder(this, SyncNotificationChannelId)
        .setSmallIcon(R.drawable.ic_aggregate_image_logo)
        .setContentTitle(getString(R.string.welcome_to_aggregate))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()
}