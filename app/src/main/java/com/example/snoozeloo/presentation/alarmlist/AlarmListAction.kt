package com.example.snoozeloo.presentation.alarmlist

sealed interface AlarmListAction {
    data class OnAlarmToggleChanged(
        val alarmID: String,
        val isAlarmEnabled: Boolean
    ) : AlarmListAction

    data object OnAlarmClicked : AlarmListAction

    data object OnCreateAlarmButtonClicked : AlarmListAction

    data class OnAlarmViewSwiped (
        val alarmID: String
    ): AlarmListAction

    data class OnAlarmViewCollapsed(
        val alarmID: String
    ) : AlarmListAction

    data class OnAlarmDeleted(
        val alarmID: String
    ): AlarmListAction
}