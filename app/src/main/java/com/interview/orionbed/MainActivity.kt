package com.interview.orionbed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.interview.orionbed.navigation.AppNavigation
import com.interview.orionbed.ui.theme.OrionbedTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This ensures edge-to-edge layout
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val mainViewModel: MainViewModel = hiltViewModel()

            OrionbedTheme {
                Scaffold(
                    bottomBar = {
                        val currentRoute =
                            navController.currentBackStackEntryAsState().value?.destination?.route
                        if (currentRoute in listOf(
                                BottomNavItem.Home.route,
                                BottomNavItem.Stats.route,
                                BottomNavItem.Schedules.route,
                                BottomNavItem.Settings.route
                            )
                        ) {
                            BottomNavigationBar(navController)
                        }
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent) // important for gradient backgrounds
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        AppNavigation(
                            navController = navController,
                            userIsLoggedIn = false,
                            mainViewModel = mainViewModel
                        )
                    }
                }
            }
        }
    }
}
