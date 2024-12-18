package com.example.snoozeloo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snoozeloo.data.database.dao.AlarmDao
import com.example.snoozeloo.data.database.entity.AlarmEntity

@Database(
    entities = [AlarmEntity::class],
    version = 1
)
abstract class AlarmsDatabase: RoomDatabase() {
    abstract val alarmDao: AlarmDao
    companion object {
        const val ALARM_DB_NAME = "alarm.db"
    }
}