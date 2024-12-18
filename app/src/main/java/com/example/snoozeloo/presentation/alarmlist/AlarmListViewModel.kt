package com.example.snoozeloo.presentation.alarmlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snoozeloo.domain.AlarmRepository
import com.example.snoozeloo.domain.alarmSettings.AlarmRemainingTimeCalculator
import com.example.snoozeloo.domain.alarmSettings.AlarmScheduler
import com.example.snoozeloo.domain.model.AlarmItem
import com.example.snoozeloo.presentation.alarmSettings.toFormattedRemainingTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(
    private val repository: AlarmRepository,
    private val remainingTimeCalculator: AlarmRemainingTimeCalculator,
    private val alarmScheduler: AlarmScheduler
) : ViewModel() {

    private var _alarmListState = MutableStateFlow(AlarmListState())
    var alarmListState = _alarmListState.asStateFlow()

    fun onAction(action: AlarmListAction) {
        when (action) {
            is AlarmListAction.OnAlarmToggleChanged -> {

                updateAlarmToggleState(action.isAlarmEnabled, action.alarmID)

                val alarm = _alarmListState.value.alarms.find {
                    it.id == action.alarmID
                }

                _alarmListState.update { alarmState ->
                    alarmState.copy(
                        alarms = alarmState.alarms.map { alarmUi ->
                            if (alarmUi.id == action.alarmID) {
                                alarmUi.copy(isEnabled = action.isAlarmEnabled)
                            } else alarmUi
                        }
                    )
                }

                if (action.isAlarmEnabled) {
                    alarm?.let {
                        scheduleAlarm(it.toAlarmItem())
                    }
                } else {
                    alarm?.let {
                        cancelAlarm(it.id)
                    }
                }
            }

            is AlarmListAction.OnAlarmViewSwiped -> {
                _alarmListState.update { alarmState ->
                    alarmState.copy(
                        alarms = alarmState.alarms.map { alarmUi ->
                            if (alarmUi.id == action.alarmID) {
                                alarmUi.copy(areOptionsRevealed = true)
                            } else alarmUi
                        }
                    )
                }
            }

            is AlarmListAction.OnAlarmViewCollapsed -> {
                _alarmListState.update { alarmState ->
                    alarmState.copy(
                        alarms = alarmState.alarms.map { alarmUi ->
                            if (alarmUi.id == action.alarmID) {
                                alarmUi.copy(areOptionsRevealed = false)
                            } else alarmUi
                        }
                    )
                }
            }

            is AlarmListAction.OnAlarmDeleted -> {
                deleteAlarm(action.alarmID)
            }

            else -> {}
        }
    }

    fun loadAlarms(isIn24HourFormat: Boolean) {
        viewModelScope.launch {
            repository.getAlarms().collect { alarms ->
                val sortedAlarms = alarms.sortedBy { it.alarmTime }
                val alarmUiList = sortedAlarms.map { it.toAlarmUi(isIn24HourFormat) }
                _alarmListState.update { it.copy(alarms = alarmUiList) }

                combine(
                    sortedAlarms.map { alarm ->
                        remainingTimeCalculator(alarm.alarmTime).map { remainingTime ->
                            alarm.id to remainingTime.toFormattedRemainingTime()
                        }
                    }
                ) { updates ->
                    updates.toMap()
                }.collect { remainingTimes ->
                    _alarmListState.update { state ->
                        val updatedAlarms = state.alarms.map { existingAlarm ->
                            val newFormattedTime = remainingTimes[existingAlarm.id]
                            if (newFormattedTime != null) {
                                existingAlarm.copy(formattedRemainingTime = newFormattedTime)
                            } else {
                                existingAlarm
                            }
                        }
                        state.copy(alarms = updatedAlarms)
                    }
                }
            }
        }
    }

    private fun updateAlarmToggleState(isAlarmEnabled: Boolean, alarmId: String) =
        viewModelScope.launch {
            if (isAlarmEnabled) {
                repository.enableAlarm(alarmId)
            } else {
                repository.disableAlarm(alarmId)
            }
        }

    private fun deleteAlarm(alarmID: String) = viewModelScope.launch {
        repository.deleteAlarm(alarmID)
        alarmScheduler.cancel(alarmID)
    }

    private fun scheduleAlarm(alarmItem:AlarmItem) {
        alarmScheduler.schedule(alarmItem)
    }

    private fun cancelAlarm(alarmID: String) {
        alarmScheduler.cancel(alarmID)
    }
}

