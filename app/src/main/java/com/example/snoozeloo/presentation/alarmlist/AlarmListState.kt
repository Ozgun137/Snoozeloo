package com.example.snoozeloo.presentation.alarmlist

import androidx.compose.runtime.Immutable
import com.example.snoozeloo.presentation.model.AlarmUi

@Immutable
data class AlarmListState(
    val alarms: List<AlarmUi> = emptyList()
)


