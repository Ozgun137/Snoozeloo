package com.example.snoozeloo.presentation.alarmSettings

import kotlin.time.Duration

fun Duration.toFormattedRemainingTime():String {
    val totalSeconds = inWholeSeconds
    val hours = (totalSeconds / (60 * 60)).toInt()

    val minutes = ((totalSeconds % 3600) / 60).toInt()

    return when {
        hours == 0 && minutes == 0 -> "0min"
        hours == 0 -> "${minutes}min"
        minutes == 0 -> "${hours}h"
        hours > 0 && minutes > 0 -> "${hours}h ${minutes}min"
        else -> ""
    }
}