@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.snoozeloo.presentation.alarmlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.snoozeloo.R
import com.example.snoozeloo.presentation.alarmlist.components.AlarmItemView
import com.example.snoozeloo.presentation.components.ActionIcon
import com.example.snoozeloo.presentation.components.SnoozelooFloatingActionButton
import com.example.snoozeloo.presentation.components.SnoozelooScaffold
import com.example.snoozeloo.presentation.components.SnoozelooToolBar
import com.example.snoozeloo.presentation.components.SwipeableItem
import com.example.snoozeloo.presentation.util.isIn24HourFormat
import com.example.snoozeloo.ui.theme.AlarmIcon
import com.example.snoozeloo.ui.theme.SnoozelooBlue
import com.example.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun AlarmListScreenRoot(
    viewModel: AlarmListViewModel = hiltViewModel(),
    onCreateAlarmClicked: () -> Unit,
) {
    val alarmListState by viewModel.alarmListState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val is24HourFormat = context.isIn24HourFormat()

    LaunchedEffect(is24HourFormat) {
        viewModel.loadAlarms(is24HourFormat)
    }

    AlarmListScreen(
        alarmsUiState = alarmListState,
        onCreateAlarmClicked = { onCreateAlarmClicked() },
        onAlarmToggleChanged = { alarmID, alarmState ->
            viewModel.onAction(
                AlarmListAction.OnAlarmToggleChanged(
                    alarmID = alarmID,
                    isAlarmEnabled = alarmState
                )
            )
        },
        onDeleteAlarmClicked = { id->
            viewModel.onAction(
                AlarmListAction.OnAlarmDeleted(id)
            )

            viewModel.loadAlarms(is24HourFormat)
        },
        onAlarmViewSwiped = { id->
            viewModel.onAction(
                AlarmListAction.OnAlarmViewSwiped(id)
            )
        },
        onAlarmViewCollapsed = { id->
            viewModel.onAction(
                AlarmListAction.OnAlarmViewCollapsed(id)
            )
        },
    )
}

@Composable
private fun AlarmListScreen(
    modifier: Modifier = Modifier,
    onCreateAlarmClicked: () -> Unit,
    onAlarmToggleChanged: (String, Boolean) -> Unit,
    alarmsUiState: AlarmListState,
    onDeleteAlarmClicked: (String) -> Unit,
    onAlarmViewSwiped: (String) -> Unit,
    onAlarmViewCollapsed: (String) -> Unit
) {

    SnoozelooScaffold(
        topAppBar = {
            SnoozelooToolBar(
                title = stringResource(R.string.your_alarms),
            )
        },

        floatingActionButton = {
            SnoozelooFloatingActionButton(
                modifier = Modifier.padding(16.dp),
                icon = Icons.Filled.Add,
                onClick = {
                    onCreateAlarmClicked()
                },
                contentDescription = stringResource(R.string.create_alarm)
            )
        }
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
              if(alarmsUiState.alarms.isNotEmpty()) {
                  AlarmsListContent(
                      modifier = modifier,
                      onAlarmToggleChanged = onAlarmToggleChanged,
                      alarmsUiState = alarmsUiState,
                      onDeleteAlarmClicked = onDeleteAlarmClicked,
                      onAlarmViewSwiped = onAlarmViewSwiped,
                      onAlarmViewCollapsed = onAlarmViewCollapsed
                  )
              }

              else {
                  AlarmListEmptyContent()
              }
        }

    }
}

@Composable
private fun AlarmsListContent(
    modifier: Modifier = Modifier,
    onAlarmToggleChanged: (String, Boolean) -> Unit,
    alarmsUiState: AlarmListState,
    onDeleteAlarmClicked: (String) -> Unit,
    onAlarmViewSwiped: (String) -> Unit,
    onAlarmViewCollapsed: (String) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = alarmsUiState.alarms,
            key = { alarmUi -> alarmUi.id }
        ) { alarmUi ->
            SwipeableItem(
                areActionsRevealed = alarmUi.areOptionsRevealed,
                onExpanded = {
                    onAlarmViewSwiped(alarmUi.id)
                },
                onCollapsed = {
                    onAlarmViewCollapsed(alarmUi.id)
                },
                actions = {
                    ActionIcon(
                        onClick = {
                            onDeleteAlarmClicked(alarmUi.id)
                        },
                        backgroundColor = SnoozelooBlue,
                        icon = Icons.Default.Delete,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            ) {
                AlarmItemView(
                    alarmToggleChanged = { alarmId, alarmState ->
                        onAlarmToggleChanged(alarmId, alarmState)
                    },
                    alarmUi = alarmUi
                )
            }
        }

    }
}

@Composable
private fun AlarmListEmptyContent() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Icon(
            imageVector = AlarmIcon,
            contentDescription = null,
            tint = SnoozelooBlue
        )

        Spacer(modifier = Modifier.height(32.dp))

       Text(
           text = stringResource(R.string.alarm_list_empty),
           style = MaterialTheme.typography.bodySmall
       )
    }

}

@Composable
@Preview
fun AlarmListScreenPreview() = SnoozelooTheme {
    val alarmsList = AlarmListPreviewParameterProvider().values.toList()
    AlarmListScreen(
        onCreateAlarmClicked = {},
        alarmsUiState = AlarmListState(
            alarms = alarmsList,
        ),
        onAlarmToggleChanged = { _, _ -> },
        onDeleteAlarmClicked = {},
        onAlarmViewSwiped = {},
        onAlarmViewCollapsed = {}
    )
}