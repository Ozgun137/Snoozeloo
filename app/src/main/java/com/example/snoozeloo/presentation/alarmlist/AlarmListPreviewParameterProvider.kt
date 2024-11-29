package com.example.snoozeloo.presentation.alarmlist

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.snoozeloo.presentation.model.AlarmUi

class AlarmListPreviewParameterProvider: PreviewParameterProvider<AlarmUi> {

    override val values = sequenceOf(
        AlarmUi(
            id = 1,
            name = "Wake Up",
            formattedAlarmTime = "10:00 AM",
            formattedRemainingTime = "Alarm in 30 min"
        ),

        AlarmUi(
            id = 2,
            name = "Education",
            formattedAlarmTime = "04:00 PM",
            formattedRemainingTime = "Alarm in 30 min"
        ),

        AlarmUi(
            id = 3,
            name = "Dinner",
            formattedAlarmTime = "06:00 PM",
            formattedRemainingTime = "Alarm in 30 min"
        )
    )
}