package com.example.snoozeloo.domain.alarmSettings

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration


class AlarmRemainingTimeCalculator {

     operator fun invoke (selectedTime:LocalDateTime) : Flow<Duration>  = flow {
         while (true) {
             val currentTime = LocalDateTime.now()
             val adjustedSelectedDateTime = if (selectedTime.isBefore(currentTime)) {
                 selectedTime.plusDays(1)
             } else {
                 selectedTime
             }

             val currentEpochMillis = currentTime.toInstant(ZoneOffset.UTC).toEpochMilli()
             val selectedEpochMillis = adjustedSelectedDateTime.toInstant(ZoneOffset.UTC).toEpochMilli()
             val durationMillis = selectedEpochMillis - currentEpochMillis
             val duration = durationMillis.toDuration(DurationUnit.MILLISECONDS)

             emit(duration)
             delay(1000L)
         }
    }
}