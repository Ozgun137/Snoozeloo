package com.example.snoozeloo.presentation.di

import com.example.snoozeloo.domain.alarmSettings.AlarmRemainingTimeCalculator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AlarmPresentationModule{

    @ViewModelScoped
    @Provides
    fun provideAlarmRemainingCalculator(): AlarmRemainingTimeCalculator = AlarmRemainingTimeCalculator()
}