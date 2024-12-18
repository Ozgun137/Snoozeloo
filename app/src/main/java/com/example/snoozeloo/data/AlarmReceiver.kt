package com.example.snoozeloo.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.provider.Settings
import com.example.snoozeloo.core.extensions.toSafeString
import com.example.snoozeloo.core.showNotificationWithFullScreenIntent
import java.time.LocalDateTime

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        private var mediaPlayer: MediaPlayer? = null
        private var currentAlarmId: String? = null

        fun stopMediaPlayer(alarmID: String) {
           if(alarmID == currentAlarmId) {
               mediaPlayer?.let {
                   if (it.isPlaying) {
                       it.stop()
                       it.release()
                       mediaPlayer = null
                   }
               }
           }
        }
    }

    override fun onReceive(context: Context, intent: Intent?) {
        val alarmUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            ?: Settings.System.DEFAULT_ALARM_ALERT_URI

        val alarmName = intent?.getStringExtra("alarmName").toSafeString()
        val alarmTime = intent?.getStringExtra("alarmTime").toSafeString()
        val alarmID = intent?.getStringExtra("alarmID").toSafeString()

        currentAlarmId = alarmID

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            setDataSource(context, alarmUri)
            prepare()
            start()
            isLooping = true
        }

        context.showNotificationWithFullScreenIntent(
            title = alarmName,
            alarmID = alarmID,
            alarmTime = alarmTime
        )
    }

}