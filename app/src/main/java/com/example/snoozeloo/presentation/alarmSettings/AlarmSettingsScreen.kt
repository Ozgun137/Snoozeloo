@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.snoozeloo.presentation.alarmSettings

import android.Manifest
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.snoozeloo.R
import com.example.snoozeloo.presentation.ObserveAsEvents
import com.example.snoozeloo.presentation.components.SnoozelooDialog
import com.example.snoozeloo.presentation.components.SnoozelooPermissionDialog
import com.example.snoozeloo.presentation.components.SnoozelooScaffold
import com.example.snoozeloo.presentation.components.SnoozelooTimePicker
import com.example.snoozeloo.presentation.components.SnoozelooToolBar
import com.example.snoozeloo.presentation.util.hasNotificationPermission
import com.example.snoozeloo.presentation.util.isIn24HourFormat
import com.example.snoozeloo.presentation.util.shouldShowNotificationPermissionRationale
import com.example.snoozeloo.ui.theme.SnoozelooGray
import com.example.snoozeloo.ui.theme.SnoozelooTheme


@Composable
fun AlarmSettingsScreenRoot(
    viewModel: AlarmSettingsViewModel = hiltViewModel(),
    onCancelClicked: () -> Unit,
) {
    val alarmSettingsState by viewModel.alarmSettingsState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is AlarmSettingsEvent.AlarmScheduled -> {
                Toast.makeText(
                    context,
                    context.getString(R.string.alarmScheduledIn, event.formattedRemainingTime),
                    Toast.LENGTH_LONG
                ).show()
            }

            AlarmSettingsEvent.AlarmSaveFailed -> {
                Toast.makeText(
                    context,
                    context.getString(R.string.error_disk_full),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    AlarmSettingsScreen(
        alarmSettingsState = alarmSettingsState,
        onCancelClicked = onCancelClicked,
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun AlarmSettingsScreen(
    modifier: Modifier = Modifier,
    alarmSettingsState: AlarmSettingsState,
    onCancelClicked: () -> Unit = {},
    onAction: (AlarmSettingsAction) -> Unit,
) {
    val context = LocalContext.current
    val activity = context as ComponentActivity

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        val showNotificationRationale = activity.shouldShowNotificationPermissionRationale()

        onAction(
            AlarmSettingsAction.SubmitNotificationPermissionInfo(
                isPermissionGranted = isGranted,
                showNotificationPermissionRationale = showNotificationRationale
            )
        )
    }

    SnoozelooScaffold(
        topAppBar = {
            SnoozelooToolBar(
                modifier = modifier,
                showCancelButton = true,
                showSaveButton = true,
                onCancelButtonClick = onCancelClicked,
                onSaveButtonClick = {
                    if(context.hasNotificationPermission()) {
                        onAction(AlarmSettingsAction.OnSaveClicked)
                    }

                    else {
                        permissionLauncher.requestNotificationPermission(context)
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(start = 16.dp, end = 16.dp)
        ) {

            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                SnoozelooTimePicker(
                    is24HourTime = context.isIn24HourFormat(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    onTimeStateValueChanged = { timePickerState ->
                        onAction(
                            AlarmSettingsAction.AlarmTimeChanged(timePickerState)
                        )
                    },
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(12.dp),
                    text = stringResource(
                        R.string.alarmIn,
                        alarmSettingsState.formattedRemainingTime
                    ),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = SnoozelooGray,
                    ),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .clickable {
                        onAction(AlarmSettingsAction.OnAlarmNameClicked)
                    },
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = stringResource(R.string.alarm_name),
                        style = MaterialTheme.typography.bodySmall,
                    )

                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = alarmSettingsState.alarmName,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

            }

            if (alarmSettingsState.shouldShowDialog) {
                SnoozelooDialog(
                    title = stringResource(R.string.alarm_name),
                    onDismiss = {
                        onAction(AlarmSettingsAction.OnAlarmDialogDismissed)
                    },
                    onSaveClicked = { alarmName ->
                        onAction(AlarmSettingsAction.AlarmNameEntered(alarmName))
                        onAction(AlarmSettingsAction.OnAlarmDialogDismissed)
                    }
                )
            }
        }
    }

    if (alarmSettingsState.showNotificationRationale) {
        SnoozelooPermissionDialog(
            title = stringResource(R.string.permission_required),
            onDismiss = {},
            description = stringResource(R.string.notification_rationale),
            dialogPrimaryButton = {
                Button(
                    onClick = {
                        permissionLauncher.requestNotificationPermission(context)
                        onAction(AlarmSettingsAction.DismissRationaleDialog)
                    }
                ) {
                    Text(text = stringResource(R.string.okay))
                }
            },
        )
    }
}

private fun ActivityResultLauncher<String>.requestNotificationPermission(
    context:Context
) {
    val hasNotificationPermission = context.hasNotificationPermission()
    val notificationPermission = if (Build.VERSION.SDK_INT >= 33) {
        Manifest.permission.POST_NOTIFICATIONS
    } else ""

    if(!hasNotificationPermission) {
        launch(notificationPermission)
    }
}


@Composable
@Preview
fun AlarmSettingsScreenPreview() = SnoozelooTheme {
    AlarmSettingsScreen(
        alarmSettingsState = AlarmSettingsState(
            alarmName = "Work",
            formattedRemainingTime = "7h 15 min"
        ),
        onAction = {}
    )
}