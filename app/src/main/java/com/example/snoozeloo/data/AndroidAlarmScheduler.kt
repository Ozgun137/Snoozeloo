package com.example.snoozeloo.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.snoozeloo.domain.alarmSettings.AlarmScheduler
import com.example.snoozeloo.domain.model.AlarmItem
import com.example.snoozeloo.presentation.alarmlist.toFormattedAlarmTime
import com.example.snoozeloo.presentation.util.isIn24HourFormat
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.ZoneId
import javax.inject.Inject

class AndroidAlarmScheduler @Inject constructor(
    @ApplicationContext private val context:Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(alarmItem: AlarmItem) {
        val intent = Intent(
            context,
            AlarmReceiver::class.java
        ).apply {
            putExtra("alarmName", alarmItem.alarmName)
            putExtra("alarmTime", alarmItem.alarmTime.toFormattedAlarmTime(context.isIn24HourFormat()))
            putExtra("alarmID", alarmItem.id)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmItem.alarmTime.atZone(ZoneId.systemDefault()).toEpochSecond() * 1_000L,
            PendingIntent.getBroadcast(
                context,
                alarmItem.id.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel(alarmID:String) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarmID.hashCode(),
                Intent(context,AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )

        AlarmReceiver.stopMediaPlayer(alarmID)
    }
}