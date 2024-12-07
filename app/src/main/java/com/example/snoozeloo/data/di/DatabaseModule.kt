package com.example.snoozeloo.data.di

import android.app.Application
import androidx.room.Room
import com.example.snoozeloo.data.database.AlarmsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAlarmDatabase(app:Application): AlarmsDatabase =
        Room.databaseBuilder(
            app,
            AlarmsDatabase::class.java,
            AlarmsDatabase.ALARM_DB_NAME
        ).build()

    @Singleton
    @Provides
    fun provideAlarmDao(alarmsDatabase: AlarmsDatabase) =
        alarmsDatabase.alarmDao
}