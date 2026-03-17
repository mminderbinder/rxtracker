package com.example.rxtracker.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.composeunstyled.Button
import com.example.rxtracker.navigation.topbar.MainTopBar
import com.example.rxtracker.navigation.topbar.SecondaryTopBar
import com.example.rxtracker.ui.history.HistoryScreen
import com.example.rxtracker.ui.home.HomeScreen
import com.example.rxtracker.ui.medications.MedicationsScreen
import com.example.rxtracker.ui.menu.AboutScreen
import com.example.rxtracker.ui.menu.PrivacyPolicyScreen
import com.example.rxtracker.ui.menu.SettingsScreen
import com.example.rxtracker.uikit.components.one.AppScaffold
import com.example.rxtracker.uikit.components.one.BottomNavigationBar
import com.example.rxtracker.uikit.components.one.Icon
import com.example.rxtracker.uikit.components.one.PrimaryTab
import com.example.rxtracker.uikit.components.one.Text

private val mainRoutes = listOf(
    AppDestination.Home.route,
    AppDestination.Medications.route,
    AppDestination.History.route,
)

private val secondaryRoutes = listOf(
    AppDestination.Settings.route,
    AppDestination.About.route,
    AppDestination.PrivacyPolicy.route,
    AppDestination.AddMedication.route,
    AppDestination.AddFrequency.route,
    AppDestination.AddDoseDetails.route,
    AppDestination.AddTimes.route,
    AppDestination.AddOptionalDetails.route
)

private val addMedicationRoutes = listOf(
    AppDestination.AddMedication.route,
    AppDestination.AddFrequency.route,
    AppDestination.AddDoseDetails.route,
    AppDestination.AddTimes.route,
    AppDestination.AddOptionalDetails.route
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    val isInAddFlow = currentRoute in addMedicationRoutes
    val showBottomNav = !isInAddFlow
    val showFab = currentRoute == AppDestination.Home.route

    AppScaffold {
        when (currentRoute) {
            in mainRoutes -> MainTopBar(navController)
            in secondaryRoutes -> SecondaryTopBar(
                title = AppDestination.fromRoute(currentRoute)?.title ?: "",
                navController = navController
            )
        }

        Box(Modifier.weight(1f)) {
            NavHost(
                navController = navController,
                startDestination = AppDestination.Home.route,
            ) {
                composable(AppDestination.Home.route) {
                    HomeScreen()
                }
                composable(AppDestination.Medications.route) {
                    MedicationsScreen()
                }
                composable(AppDestination.History.route) {
                    HistoryScreen()
                }
                composable(AppDestination.Settings.route) {
                    SettingsScreen()
                }
                composable(AppDestination.About.route) {
                    AboutScreen()
                }
                composable(AppDestination.PrivacyPolicy.route) {
                    PrivacyPolicyScreen()
                }
            }

            if (showFab) {
                Button(
                    onClick = { navController.navigate(AppDestination.AddMedication.route) },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .navigationBarsPadding()
                        .padding(end = 16.dp, bottom = 16.dp)
                ) {
                    Icon(imageVector = Lucide.Plus, contentDescription = "Add medication")
                }
            }
        }

        if (showBottomNav) {
            BottomNavigationBar {
                bottomNavDestinations.forEach { destination ->
                    PrimaryTab(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == destination.route
                        } == true,
                        onSelected = { navController.navigate(destination.route) },
                        modifier = Modifier.weight(1f),
                        icon = {
                            destination.icon?.let {
                                Icon(imageVector = it, contentDescription = destination.title)
                            }
                        },
                        title = { Text(destination.title) }
                    )
                }
            }
        }
    }
}