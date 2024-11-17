package com.example.snoozeloo.presentation.alarmlist

sealed interface AlarmListAction {
    data class OnAlarmToggleChanged(
        val alarmID: Int,
        val isAlarmEnabled: Boolean
    ) : AlarmListAction

    data object OnAlarmClicked : AlarmListAction

    data object OnCreateAlarmButtonClicked : AlarmListAction

    data class OnAlarmViewSwiped (
        val alarmID: Int
    ): AlarmListAction

    data class OnAlarmViewCollapsed(
        val alarmID: Int
    ) : AlarmListAction

    data class OnAlarmDeleted(
        val alarmID: Int
    ): AlarmListAction
}