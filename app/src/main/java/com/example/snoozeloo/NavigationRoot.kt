package com.example.snoozeloo

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.snoozeloo.presentation.alarmSettings.AlarmSettingsScreenRoot
import com.example.snoozeloo.presentation.alarmlist.AlarmListScreenRoot
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
          AlarmListScreenRoot(
             onCreateAlarmClicked = {
                 navController.navigate(AlarmSettings)
             }
          )
        }

        composable<AlarmSettings> {
            AlarmSettingsScreenRoot(
                onCancelClicked = {
                    navController.navigateUp()
                }
            )
        }
    }
}

@Serializable
object Alarms

@Serializable
object AlarmList

@Serializable
object AlarmSettings

