@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.snoozeloo.presentation.alarmSettings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snoozeloo.domain.alarmSettings.AlarmRemainingTimeCalculator
import com.example.snoozeloo.domain.alarmSettings.AlarmScheduler
import com.example.snoozeloo.domain.model.AlarmItem
import com.example.snoozeloo.presentation.alarmSettings.AlarmSettingsAction.AlarmNameEntered
import com.example.snoozeloo.presentation.alarmSettings.AlarmSettingsAction.AlarmTimeChanged
import com.example.snoozeloo.presentation.alarmSettings.AlarmSettingsAction.OnSaveClicked
import com.example.snoozeloo.presentation.alarmSettings.AlarmSettingsAction.OnAlarmNameClicked
import com.example.snoozeloo.presentation.alarmSettings.AlarmSettingsAction.OnAlarmDialogDismissed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AlarmSettingsViewModel @Inject constructor(
    private val alarmRemainingTimeCalculator: AlarmRemainingTimeCalculator,
    private val alarmScheduler: AlarmScheduler
) : ViewModel() {

    private val _alarmSettingsState = MutableStateFlow(AlarmSettingsState())
    val alarmSettingsState = _alarmSettingsState.asStateFlow()

    private val eventChannel = Channel<AlarmSettingsEvent>()
    val events = eventChannel.receiveAsFlow()

    private var remainingTimeJob: Job? = null


    fun onAction(action: AlarmSettingsAction) {
        when (action) {
            is AlarmTimeChanged -> {
                val currentTime = LocalDateTime.now()
                val selectedDateTime = LocalDateTime.of(
                    currentTime.toLocalDate(),
                    LocalTime.of(action.alarmState.hour, action.alarmState.minute)
                )

                calculateRemainingTime(
                    selectedTime = selectedDateTime
                )

                _alarmSettingsState.update {
                    it.copy(
                        hour = action.alarmState.hour,
                        minutes = action.alarmState.minute
                    )
                }
            }

            is AlarmNameEntered -> {
                _alarmSettingsState.update {
                    it.copy(alarmName = action.name)
                }
            }

            OnSaveClicked -> {
                val hour = _alarmSettingsState.value.hour
                val minute = _alarmSettingsState.value.minutes

                alarmScheduler.schedule(
                    alarmItem = AlarmItem(
                        alarmTime = LocalDateTime
                            .now()
                            .withHour(hour)
                            .withMinute(minute)
                            .withSecond(0)
                            .withNano(0),
                        alarmName = _alarmSettingsState.value.alarmName
                    )
                )

                viewModelScope.launch {
                    eventChannel.send(
                        AlarmSettingsEvent.AlarmScheduled(
                            _alarmSettingsState.value.formattedRemainingTime
                        )
                    )
                }
            }

            OnAlarmNameClicked -> {
                _alarmSettingsState.update {
                    it.copy(shouldShowDialog = true)
                }
            }

            OnAlarmDialogDismissed -> {
                _alarmSettingsState.update {
                    it.copy(shouldShowDialog = false)
                }
            }

            else -> {}

        }
    }

    private fun calculateRemainingTime(selectedTime: LocalDateTime) {
        remainingTimeJob?.cancel()

        remainingTimeJob = alarmRemainingTimeCalculator(
            selectedTime = selectedTime
        ).onEach { remainingTime ->
            _alarmSettingsState.update {
                it.copy(
                    formattedRemainingTime = remainingTime.toFormattedRemainingTime()
                )
            }
        }.launchIn(viewModelScope)
    }
}