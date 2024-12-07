package com.example.snoozeloo.presentation.alarmSettings

sealed interface AlarmSettingsEvent {
    data object AlarmSaveFailed: AlarmSettingsEvent
    data class AlarmScheduled(val formattedRemainingTime: String) :  AlarmSettingsEvent
}