package com.interview.orionbed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.interview.orionbed.BottomNavItem
import com.interview.orionbed.MainViewModel
import com.interview.orionbed.SettingsScreen
import com.interview.orionbed.home.TemperatureScreen
import com.interview.orionbed.login.CredentialEntryScreen
import com.interview.orionbed.login.HardwareSelectScreen
import com.interview.orionbed.login.LoginResult
import com.interview.orionbed.login.PrimarySetupScreen
import com.interview.orionbed.login.SecondarySetupScreen
import com.interview.orionbed.login.WaitingApprovalScreen
import com.interview.orionbed.schedules.SchedulesScreen
import com.interview.orionbed.stats.StatsScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    userIsLoggedIn: Boolean,
    mainViewModel: MainViewModel
) {
    val initData = mainViewModel.initData.value

    NavHost(
        navController = navController,
        startDestination = if (userIsLoggedIn) "main" else "auth"
    ) {
        // 🔒 Pre-login flow
        navigation(startDestination = "hardware_select", route = "auth") {
            composable("hardware_select") {
                HardwareSelectScreen(
                    onDeviceSelected = { hardwareId ->
                        navController.navigate("login/$hardwareId")
                    }
                )
            }

            composable("login/{hardwareId}") { backStackEntry ->
                val hardwareId = backStackEntry.arguments?.getString("hardwareId") ?: ""
                CredentialEntryScreen(
                    hardwareId = hardwareId,
                    onContinue = { result ->
                        when (result) {
                            LoginResult.PRIMARY_SETUP -> navController.navigate("primary_setup")
                            LoginResult.SECONDARY_APPROVED -> navController.navigate("secondary_setup/Left")
                            LoginResult.SECONDARY_PENDING -> navController.navigate("waiting_approval")
                            LoginResult.SECONDARY_DENIED -> {
                                // TODO: Show a "request denied" screen or dialog
                            }

                            LoginResult.ERROR -> {
                                // TODO: Show a Snackbar or error screen
                            }
                        }
                    }
                )
            }

            composable("primary_setup") {
                PrimarySetupScreen(
                    onSideSelected = { selectedSide ->
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo("auth") { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable("secondary_setup/{primarySide}") { backStackEntry ->
                val primarySide = backStackEntry.arguments?.getString("primarySide") ?: ""
                SecondarySetupScreen(
                    primarySide = primarySide,
                    onSideSelected = {
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo("auth") { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable("waiting_approval") {
                WaitingApprovalScreen(
                    onApproved = {
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo("auth") { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }

        // 🏠 Main post-login flow with bottom navigation
        navigation(startDestination = BottomNavItem.Home.route, route = "main") {
            composable(BottomNavItem.Home.route) {
                TemperatureScreen(
                    recommendedBedtime = initData?.recommendedBedtime ?: "10:00 PM",
                    currentTemperature = initData?.currentTemperature ?: 72
                )
            }
            composable(BottomNavItem.Stats.route) {
                StatsScreen(stats = initData?.stats ?: emptyList())
            }
            composable(BottomNavItem.Schedules.route) {
                SchedulesScreen(schedules = initData?.schedules ?: emptyList())
            }
            composable(BottomNavItem.Settings.route) {
                SettingsScreen()
            }
        }

    }
}
