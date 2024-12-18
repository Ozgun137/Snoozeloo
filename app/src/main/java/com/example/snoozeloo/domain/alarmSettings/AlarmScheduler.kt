package com.example.snoozeloo.domain.alarmSettings

import com.example.snoozeloo.domain.model.AlarmItem

interface AlarmScheduler {
    fun schedule(alarmItem: AlarmItem)
    fun cancel(alarmID: String)
}