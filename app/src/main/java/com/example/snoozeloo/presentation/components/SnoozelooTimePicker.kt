@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.snoozeloo.presentation.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.snoozeloo.ui.theme.SnoozelooTheme
import java.time.LocalDateTime
import java.util.Calendar

@Composable
fun SnoozelooTimePicker(
  modifier: Modifier = Modifier,
  onTimeStateValueChanged: (TimePickerState) -> Unit,
  is24HourTime: Boolean = false
) {


    val timePickerState = rememberTimePickerState(
        initialHour = LocalDateTime.now().hour,
        initialMinute = LocalDateTime.now().minute,
        is24Hour = is24HourTime,
    )

    LaunchedEffect(timePickerState.hour, timePickerState.minute) {
        onTimeStateValueChanged(timePickerState)
    }

    Box(modifier = modifier.fillMaxWidth()) {
        TimeInput(
            modifier = modifier,
            state = timePickerState,
        )
    }
    
}

@Preview
@Composable
private fun SnoozelooTimePickerPreview() = SnoozelooTheme {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SnoozelooTimePicker(
            onTimeStateValueChanged = {},
            modifier = Modifier.fillMaxSize(),
        )
    }

}