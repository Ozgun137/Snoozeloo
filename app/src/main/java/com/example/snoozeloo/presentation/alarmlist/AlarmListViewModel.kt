package com.example.snoozeloo.presentation.alarmlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snoozeloo.presentation.model.AlarmUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor (
): ViewModel() {

    private var _alarmListState = MutableStateFlow(AlarmListState())
    var alarmListState = _alarmListState
        .onStart { loadAlarms() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            AlarmListState()
        )

    fun onAction(action:AlarmListAction) {
        when(action) {
            is AlarmListAction.OnAlarmToggleChanged -> {
                _alarmListState.update { alarmState->
                    alarmState.copy(
                        alarms = alarmState.alarms.map { alarmUi ->
                            if(alarmUi.id == action.alarmID) {
                                alarmUi.copy(isEnabled = action.isAlarmEnabled)
                            } else alarmUi
                        }
                    )
                }
            }

            AlarmListAction.OnAlarmClicked -> {}
            AlarmListAction.OnCreateAlarmButtonClicked -> {}
        }
    }


    private fun loadAlarms() {
        val alarmsListMock = listOf(
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

        _alarmListState.update {
            it.copy(
                alarms = alarmsListMock
            )
        }
    }

}