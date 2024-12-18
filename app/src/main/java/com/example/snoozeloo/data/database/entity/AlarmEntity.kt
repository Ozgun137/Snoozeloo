package com.example.snoozeloo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarmEntity")
data class AlarmEntity(
   @PrimaryKey(autoGenerate = false)
   val id: String,
   val alarmName: String,
   val hour: Int,
   val minute: Int,
   val  isAlarmEnabled: Boolean
)
