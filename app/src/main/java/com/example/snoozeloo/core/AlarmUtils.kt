package com.example.snoozeloo.core

import android.app.Activity
import android.app.KeyguardManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.WindowManager
import androidx.core.app.NotificationCompat
import com.example.snoozeloo.R
import com.example.snoozeloo.presentation.triggerAlarm.TriggerAlarmActivity

fun Context.showNotificationWithFullScreenIntent(
    channelId: String = CHANNEL_ID,
    title: String,
    alarmID: String,
    alarmTime: String,
    description: String = ""
) {
    val notificationBuilder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.drawable.ic_alarm)
        .setContentTitle(title)
        .setContentText(description)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setCategory(NotificationCompat.CATEGORY_ALARM)
        .setFullScreenIntent(
            getFullScreenIntent(
                alarmID = alarmID,
                alarmName = title,
                alarmTime = alarmTime,
            ),
            true
        )

    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    with(notificationManager) {
        buildNotificationChannel()
        val notification = notificationBuilder.build()
        notify(alarmID.hashCode(), notification)
    }
}

private fun Context.getFullScreenIntent(
    alarmID: String,
    alarmName: String,
    alarmTime: String
): PendingIntent {
    val intent = Intent(this, TriggerAlarmActivity::class.java).apply {
        putExtra("alarmID", alarmID)
        putExtra("alarmName", alarmName)
        putExtra("alarmTime", alarmTime)
    }
    return PendingIntent.getActivity(
        this, alarmID.hashCode(), intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}

private fun NotificationManager.buildNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Alarm",
            NotificationManager.IMPORTANCE_HIGH
        )
        createNotificationChannel(channel)
    }
}

private const val CHANNEL_ID = "channelId"

fun Activity.turnScreenOnAndKeyguardOff() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
        setShowWhenLocked(true)
        setTurnScreenOn(true)
    } else {
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
        )
    }

    with(getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requestDismissKeyguard(this@turnScreenOnAndKeyguardOff, null)
        }
    }
}

fun Activity.turnScreenOffAndKeyguardOn() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
        setShowWhenLocked(false)
        setTurnScreenOn(false)
    } else {
        window.clearFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
        )
    }
}