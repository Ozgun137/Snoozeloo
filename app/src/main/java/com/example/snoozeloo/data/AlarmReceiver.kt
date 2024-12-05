package com.example.snoozeloo.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.provider.Settings
import com.example.snoozeloo.core.extensions.toSafeString
import com.example.snoozeloo.core.showNotificationWithFullScreenIntent

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        private var mediaPlayer: MediaPlayer? = null

        fun stopAlarmSound() {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    it.stop()
                    it.release()
                    mediaPlayer = null
                }
            }
        }
    }

    override fun onReceive(context: Context, intent: Intent?) {
        val alarmUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ?: Settings.System.DEFAULT_ALARM_ALERT_URI

        val alarmName = intent?.getStringExtra("alarmName").toSafeString()

      /*  mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            setDataSource(context, alarmUri)
            isLooping = true
            prepare()
            start()
        }*/

        context.showNotificationWithFullScreenIntent(
            title = alarmName
        )
    }

}