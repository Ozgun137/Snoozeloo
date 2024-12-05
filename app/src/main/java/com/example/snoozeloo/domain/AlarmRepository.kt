package com.example.snoozeloo.domain

import com.example.snoozeloo.domain.model.AlarmItem
import com.example.snoozeloo.domain.util.DataError
import com.example.snoozeloo.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
   suspend fun upsertAlarm(alarmItem: AlarmItem) : Result<Unit, DataError.Local>
   fun getAlarms(): Flow<List<AlarmItem>>
   suspend fun deleteAlarm(id:Int)
}