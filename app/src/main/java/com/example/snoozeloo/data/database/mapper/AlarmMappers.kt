package com.example.snoozeloo.data.database.mapper

import com.example.snoozeloo.data.database.entity.AlarmEntity
import com.example.snoozeloo.domain.model.AlarmItem
import java.time.LocalDateTime

fun AlarmEntity.toAlarmItem(): AlarmItem {
   return AlarmItem(
       isAlarmEnabled = isAlarmEnabled,
       alarmName = alarmName,
       alarmTime = LocalDateTime
           .now()
           .withHour(hour)
           .withMinute(minute)
           .withSecond(0)
           .withNano(0)
   )
}

fun AlarmItem.toAlarmEntity(): AlarmEntity {
    return AlarmEntity(
        alarmName = alarmName,
        isAlarmEnabled = isAlarmEnabled,
        hour = alarmTime.hour,
        minute = alarmTime.minute
    )
}