package com.example.snoozeloo.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.snoozeloo.data.database.entity.AlarmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Upsert
    suspend fun upsertAlarm(alarm:AlarmEntity)

    @Query("SELECT* FROM alarmEntity")
    fun getAlarms(): Flow<List<AlarmEntity>>

    @Query("DELETE FROM alarmEntity WHERE id=:id")
    suspend fun deleteAlarm(id:Int)
}