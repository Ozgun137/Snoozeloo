package com.example.snoozeloo.domain.model

import java.time.LocalDateTime

data class AlarmItem(
    val isAlarmEnabled: Boolean,
    val alarmTime: LocalDateTime,
    val alarmName: String
)
