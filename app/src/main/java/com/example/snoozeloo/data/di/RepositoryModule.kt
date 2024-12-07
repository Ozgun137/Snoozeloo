package com.example.snoozeloo.data.di

import com.example.snoozeloo.data.RoomLocalAlarmDataSource
import com.example.snoozeloo.domain.AlarmRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideLocalAlarmDataSource(
        roomLocalAlarmDataSource: RoomLocalAlarmDataSource
    ): AlarmRepository
}