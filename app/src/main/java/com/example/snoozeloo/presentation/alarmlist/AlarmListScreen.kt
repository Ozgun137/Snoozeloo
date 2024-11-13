package com.example.snoozeloo.presentation.alarmlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.snoozeloo.R
import com.example.snoozeloo.presentation.alarmlist.components.AlarmItemView
import com.example.snoozeloo.presentation.components.SnoozelooFloatingActionButton
import com.example.snoozeloo.presentation.components.SnoozelooScaffold
import com.example.snoozeloo.presentation.components.SnoozelooToolBar
import com.example.snoozeloo.presentation.model.AlarmUi
import com.example.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun AlarmListScreenRoot(
    viewModel: AlarmListViewModel = hiltViewModel(),
) {
    val alarmListState by viewModel.alarmListState.collectAsStateWithLifecycle()

    AlarmListScreen(
        alarmsUiState = alarmListState,
        onCreateAlarmClicked = {},
        onAlarmToggleChanged = { alarmID, alarmState ->
            viewModel.onAction(
                AlarmListAction.OnAlarmToggleChanged(
                    alarmID = alarmID,
                    isAlarmEnabled = alarmState
                )
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmListScreen(
    modifier: Modifier = Modifier,
    onCreateAlarmClicked: () -> Unit,
    onAlarmToggleChanged: (Int, Boolean) -> Unit,
    alarmsUiState: AlarmListState
) {

    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )

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
                onClick = {},
                contentDescription = stringResource(R.string.create_alarm)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = alarmsUiState.alarms,
                key = { it.id }
            ) {
                AlarmItemView(
                    alarmToggleChanged = { alarmId, alarmState ->
                        onAlarmToggleChanged(alarmId, alarmState)
                    },
                    alarmUi = it
                )
            }

        }
    }

}

@Composable
@Preview
fun AlarmListScreenPreview() = SnoozelooTheme {
    val alarmsList = listOf(
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
        ),
    )
    AlarmListScreen(
        onCreateAlarmClicked = {},
        //Use Preview parameter provider for this preview
        alarmsUiState = AlarmListState(
            alarms = alarmsList,
        ),
        onAlarmToggleChanged = { _, _ -> }
    )
}