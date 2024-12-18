package com.example.snoozeloo.presentation.model


data class AlarmUi(
    val id: String = "0",
    val name: String = "",
    val formattedAlarmTime: String,
    val hour:Int,
    val minute: Int,
    val formattedRemainingTime:String,
    val isEnabled: Boolean = false,
    val areOptionsRevealed: Boolean = false,
)
