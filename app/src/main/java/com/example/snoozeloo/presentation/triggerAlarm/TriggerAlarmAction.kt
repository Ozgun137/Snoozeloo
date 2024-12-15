package com.example.snoozeloo.presentation.triggerAlarm

sealed interface TriggerAlarmAction {
    data class OnTurnOffAlarmClicked(val alarmID: String) : TriggerAlarmAction
}