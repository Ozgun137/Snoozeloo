package com.example.snoozeloo.domain.di

import com.example.snoozeloo.data.AndroidAlarmScheduler
import com.example.snoozeloo.domain.alarmSettings.AlarmScheduler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AlarmDomainModule {

    @Binds
    abstract fun provideAlarmScheduler(
        androidAlarmScheduler: AndroidAlarmScheduler
    ): AlarmScheduler
}