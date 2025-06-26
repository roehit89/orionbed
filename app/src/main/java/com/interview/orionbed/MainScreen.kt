package com.interview.orionbed

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.interview.orionbed.home.TemperatureScreen
import com.interview.orionbed.schedules.SchedulesScreen
import com.interview.orionbed.stats.StatsScreen


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()
    val data = viewModel.initData.value

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                TemperatureScreen(
                    recommendedBedtime = data?.recommendedBedtime ?: "",
                    currentTemperature = data?.currentTemperature ?: 74
                )
            }
            composable(BottomNavItem.Stats.route) {
                Log.i("zebra stats = ", data?.stats.toString())
                StatsScreen(stats = data?.stats.orEmpty())
            }
            composable(BottomNavItem.Schedules.route) {
                SchedulesScreen(schedules = data?.schedules.orEmpty())
            }
            composable(BottomNavItem.Settings.route) {
                SettingsScreen()
            }
        }
    }
}
