package com.example.snoozeloo.presentation.alarmSettings

import kotlin.time.Duration

fun Duration.toFormattedRemainingTime(): String {
    val totalSeconds = inWholeSeconds
    var hours = (totalSeconds / (60 * 60)).toInt()

    var minutes = ((totalSeconds % 3600) / 60).toInt() + if (totalSeconds % 60 > 0) 1 else 0

    if (minutes >= 60) {
        hours += minutes / 60
        minutes %= 60
    }

    if (hours == 0 && minutes == 0) {
        return "0min"
    }

    return when {
        hours == 0 -> "${minutes}min"
        minutes == 0 -> "${hours}h"
        else -> "${hours}h ${minutes}min"
    }
}

