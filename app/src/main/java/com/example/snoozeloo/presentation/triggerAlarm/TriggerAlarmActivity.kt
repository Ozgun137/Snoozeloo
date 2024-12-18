package com.example.snoozeloo.presentation.triggerAlarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.snoozeloo.core.extensions.toSafeString
import com.example.snoozeloo.core.turnScreenOffAndKeyguardOn
import com.example.snoozeloo.core.turnScreenOnAndKeyguardOff
import com.example.snoozeloo.ui.theme.SnoozelooTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TriggerAlarmActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        turnScreenOnAndKeyguardOff()

        val alarmID = intent.getStringExtra("alarmID").toSafeString()
        val alarmTime = intent.getStringExtra("alarmTime").toSafeString()
        val alarmName = intent.getStringExtra("alarmName").toSafeString()

        setContent {
             SnoozelooTheme {
                 Surface (
                     modifier = Modifier.fillMaxSize(),
                     color = MaterialTheme.colorScheme.background
                 ) {
                    TriggerAlarmScreenRoot(
                        alarmID = alarmID,
                        alarmName = alarmName,
                        alarmTime = alarmTime
                    )
                 }
             }
         }

    }

    override fun onDestroy() {
        super.onDestroy()
        turnScreenOffAndKeyguardOn()
    }

}