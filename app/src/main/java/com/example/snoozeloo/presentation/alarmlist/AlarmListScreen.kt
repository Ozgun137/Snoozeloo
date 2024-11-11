package com.example.snoozeloo.presentation.alarmlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snoozeloo.R
import com.example.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun AlarmListScreen(
    modifier: Modifier = Modifier,
    onCreateAlarmClicked: () -> Unit
) {

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ){
        Text(
            text = stringResource(R.string.your_alarms),
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

@Composable
@Preview
fun AlarmListScreenPreview() = SnoozelooTheme {
    AlarmListScreen(
        onCreateAlarmClicked = {}
    )
}