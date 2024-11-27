@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.snoozeloo.presentation.alarmSettings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snoozeloo.domain.alarmSettings.AlarmRemainingTimeCalculator
import com.example.snoozeloo.presentation.alarmSettings.AlarmSettingsAction.AlarmNameEntered
import com.example.snoozeloo.presentation.alarmSettings.AlarmSettingsAction.AlarmTimeChanged
import com.example.snoozeloo.presentation.alarmSettings.AlarmSettingsAction.OnSaveClicked
import com.example.snoozeloo.presentation.alarmSettings.AlarmSettingsAction.OnAlarmNameClicked
import com.example.snoozeloo.presentation.alarmSettings.AlarmSettingsAction.OnAlarmDialogDismissed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AlarmSettingsViewModel @Inject constructor(
    private val alarmRemainingTimeCalculator: AlarmRemainingTimeCalculator
) : ViewModel() {

    private val _alarmSettingsState = MutableStateFlow(AlarmSettingsState())
    val alarmSettingsState = _alarmSettingsState.asStateFlow()

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
            }

            is AlarmNameEntered -> {

            }

            OnSaveClicked -> {

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