package com.example.snoozeloo.presentation.model

data class AlarmUi(
    val id: Int,
    val name: String,
    val alarmTime: String,
    val isEnabled: Boolean = false
)
