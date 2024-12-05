package com.example.snoozeloo.data

import android.database.sqlite.SQLiteException
import com.example.snoozeloo.data.database.dao.AlarmDao
import com.example.snoozeloo.data.database.mapper.toAlarmEntity
import com.example.snoozeloo.data.database.mapper.toAlarmItem
import com.example.snoozeloo.domain.AlarmRepository
import com.example.snoozeloo.domain.model.AlarmItem
import com.example.snoozeloo.domain.util.DataError
import com.example.snoozeloo.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalAlarmDataSource(
    private val roomDao: AlarmDao
) : AlarmRepository {

    override suspend fun upsertAlarm(alarmItem: AlarmItem): Result<Unit, DataError.Local> {
        return try {
            val alarmEntity = alarmItem.toAlarmEntity()
            roomDao.upsertAlarm(alarmEntity)
            Result.Success(Unit)
        } catch (e:SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun getAlarms(): Flow<List<AlarmItem>> =
        roomDao.getAlarms().map { alarmEntities->
            alarmEntities.map {
                it.toAlarmItem()
            }
        }

    override suspend fun deleteAlarm(id: Int) {
        roomDao.deleteAlarm(id)
    }
}