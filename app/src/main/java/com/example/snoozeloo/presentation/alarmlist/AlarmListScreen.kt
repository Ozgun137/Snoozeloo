package com.example.snoozeloo.presentation.alarmlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.snoozeloo.R
import com.example.snoozeloo.presentation.components.SnoozelooFloatingActionButton
import com.example.snoozeloo.presentation.components.SnoozelooScaffold
import com.example.snoozeloo.presentation.components.SnoozelooToolBar
import com.example.snoozeloo.ui.theme.SnoozelooTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmListScreen(
    modifier: Modifier = Modifier,
    onCreateAlarmClicked: () -> Unit
) {

    SnoozelooScaffold (
        topAppBar = {
             SnoozelooToolBar(
                 title = stringResource(R.string.your_alarms),
             )
        },

        floatingActionButton = {
            SnoozelooFloatingActionButton(
                modifier = Modifier.padding(16.dp),
                icon = Icons.Filled.Add,
                onClick = {},
                contentDescription = stringResource(R.string.create_alarm)
            )
        }
    ) { padding->

    }

}

@Composable
@Preview
fun AlarmListScreenPreview() = SnoozelooTheme {
    AlarmListScreen(
        onCreateAlarmClicked = {}
    )
}