package com.example.snoozeloo.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class AlarmItem(
    val id: String = UUID.randomUUID().toString(),
    val isAlarmEnabled: Boolean,
    val alarmTime: LocalDateTime,
    val alarmName: String
)
