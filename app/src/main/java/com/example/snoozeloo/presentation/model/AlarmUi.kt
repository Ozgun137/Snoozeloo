package com.example.snoozeloo.presentation.model


data class AlarmUi(
    val id: Int = 0,
    val name: String? = "",
    val formattedAlarmTime: String,
    val formattedRemainingTime:String,
    val isEnabled: Boolean = false,
    val areOptionsRevealed: Boolean = false
)
