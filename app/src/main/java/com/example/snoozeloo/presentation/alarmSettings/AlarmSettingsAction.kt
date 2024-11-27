@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.snoozeloo.presentation.alarmSettings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState

sealed interface AlarmSettingsAction {
    data class AlarmTimeChanged(val alarmState: TimePickerState) : AlarmSettingsAction
    data class AlarmNameEntered(val name:String) : AlarmSettingsAction
    data object OnCancelClicked : AlarmSettingsAction
    data object OnSaveClicked : AlarmSettingsAction
    data object OnAlarmNameClicked: AlarmSettingsAction
    data object OnAlarmDialogDismissed: AlarmSettingsAction
}