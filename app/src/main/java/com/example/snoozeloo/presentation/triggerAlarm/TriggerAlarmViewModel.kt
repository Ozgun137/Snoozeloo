package com.example.snoozeloo.presentation.triggerAlarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snoozeloo.domain.AlarmRepository
import com.example.snoozeloo.domain.alarmSettings.AlarmScheduler
import com.example.snoozeloo.presentation.triggerAlarm.TriggerAlarmAction.OnTurnOffAlarmClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriggerAlarmViewModel @Inject constructor(
    private val alarmScheduler: AlarmScheduler,
    private val repository: AlarmRepository
) : ViewModel() {

    fun onAction(action:TriggerAlarmAction) {
        when(action) {
            is OnTurnOffAlarmClicked -> {
                alarmScheduler.cancel(action.alarmID)
                updateAlarmToggleState(action.alarmID)
            }
        }
    }

    private fun updateAlarmToggleState(alarmID:String) = viewModelScope.launch {
        repository.disableAlarm(alarmID)
    }
}