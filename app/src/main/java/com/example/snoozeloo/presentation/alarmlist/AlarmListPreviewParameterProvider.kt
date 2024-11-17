package com.example.snoozeloo.presentation.alarmlist

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.snoozeloo.presentation.model.AlarmUi

class AlarmListPreviewParameterProvider: PreviewParameterProvider<AlarmUi> {

    override val values = sequenceOf(
        AlarmUi(
            id = 1,
            name = "Wake Up",
            alarmTime = "10:00 AM"
        ),

        AlarmUi(
            id = 2,
            name = "Education",
            alarmTime = "04:00 PM"
        ),

        AlarmUi(
            id = 3,
            name = "Dinner",
            alarmTime = "06:00 PM"
        )
    )
}