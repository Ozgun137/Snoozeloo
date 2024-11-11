package com.example.snoozeloo

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.snoozeloo.presentation.alarmlist.AlarmListScreen
import kotlinx.serialization.Serializable

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Alarms
    ) {
        alarmsGraph(navController)
    }
}

private fun NavGraphBuilder.alarmsGraph(
    navController: NavHostController
) {
    navigation<Alarms>(startDestination = AlarmList) {
        composable<AlarmList> {
          AlarmListScreen(
              onCreateAlarmClicked = {}
          )
        }
    }
}

@Serializable
object Alarms

@Serializable
object AlarmList

