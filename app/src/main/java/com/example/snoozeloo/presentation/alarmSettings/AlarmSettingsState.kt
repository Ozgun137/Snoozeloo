package com.example.snoozeloo.presentation.alarmSettings

data class AlarmSettingsState(
    val isCancelEnabled: Boolean = true,
    val isSaveEnabled: Boolean = true,
    val formattedRemainingTime: String = "",
    val shouldShowDialog: Boolean = false,
    val hour: Int = 0,
    val minutes: Int = 0,
    val alarmName: String = "",
    val showNotificationRationale: Boolean = false,
)
