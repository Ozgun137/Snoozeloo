package com.example.snoozeloo.presentation.alarmlist

import com.example.snoozeloo.domain.model.AlarmItem
import com.example.snoozeloo.presentation.model.AlarmUi
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun AlarmItem.toAlarmUi(
    isIn24HourFormat: Boolean): AlarmUi {
    return AlarmUi(
        id = id,
        name = alarmName,
        formattedAlarmTime = alarmTime.toFormattedAlarmTime(isIn24HourFormat = isIn24HourFormat),
        formattedRemainingTime = "",
        isEnabled = isAlarmEnabled,
        minute = alarmTime.minute,
        hour = alarmTime.hour
    )
}

fun AlarmUi.toAlarmItem():AlarmItem {
    var alarmTime = LocalDateTime
        .now()
        .withHour(hour)
        .withMinute(minute)
        .withSecond(0)
        .withNano(0)

    val currentTime = LocalDateTime.now()

    if(alarmTime.isBefore(currentTime)) {
        alarmTime = alarmTime.plusDays(1)
    }

    return AlarmItem(
        id = id,
        isAlarmEnabled = isEnabled,
        alarmName = name,
        alarmTime = alarmTime
    )
}

fun LocalDateTime.toFormattedAlarmTime(isIn24HourFormat: Boolean): String {
    val zonedDateTime = this.atZone(ZoneId.systemDefault())

    val formatter = if (isIn24HourFormat) {
        DateTimeFormatter.ofPattern("HH:mm")
    } else {
        DateTimeFormatter.ofPattern("hh:mm a")
    }

    return formatter.format(zonedDateTime)
}
