package com.example.rxtracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

private val mainRoutes = listOf(
    AppDestination.Home.route,
    AppDestination.Medications.route,
    AppDestination.Reminders.route,
    AppDestination.Appointments.route
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


}
