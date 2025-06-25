package com.interview.orionbed.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.interview.orionbed.BottomNavItem
import com.interview.orionbed.BottomNavigationBar
import com.interview.orionbed.stats.StatsScreen
import com.interview.orionbed.ui.theme.OrionGradient


@Composable
fun MainScreen() {
    val navController = rememberNavController()

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
                TemperatureScreen()
            }
            composable(BottomNavItem.Stats.route) {
                StatsScreen()
            }
            composable(BottomNavItem.Schedules.route) {
                SchedulesScreen()
            }
            composable(BottomNavItem.Settings.route) {
                SettingsScreen()
            }
        }
    }
}

@Composable
fun SchedulesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OrionGradient), contentAlignment = Alignment.Center
    ) {
        Text("Schedules Screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OrionGradient),
        contentAlignment = Alignment.Center
    ) {
        Text("Settings Screen")
    }
}

