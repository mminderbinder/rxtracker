package com.example.rxtracker.navigation.topbar

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composables.icons.lucide.EllipsisVertical
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Pill
import com.composeunstyled.theme.Theme
import com.example.rxtracker.navigation.AppDestination
import com.example.rxtracker.uikit.components.one.DropdownMenu
import com.example.rxtracker.uikit.components.one.DropdownMenuItem
import com.example.rxtracker.uikit.components.one.DropdownMenuPanel
import com.example.rxtracker.uikit.components.one.Icon
import com.example.rxtracker.uikit.components.one.IconButton
import com.example.rxtracker.uikit.components.one.Text
import com.example.rxtracker.uikit.components.one.TopAppBar
import com.example.rxtracker.uikit.styling.RxTrackerTheme
import com.example.rxtracker.uikit.styling.colors
import com.example.rxtracker.uikit.styling.navigation
import com.example.rxtracker.uikit.styling.onNavigation

@Composable
fun MainTopBar(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val darkTheme = isSystemInDarkTheme()

    TopAppBar(
        backgroundColor = if (darkTheme) Theme[colors][navigation] else Color(0xFF2563EB),
        contentColor = if (darkTheme) Theme[colors][onNavigation] else Color.White,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Lucide.Pill,
                    contentDescription = null
                )
                Text("RxTracker")
            }
        },
        actions = {
            DropdownMenu(
                onExpandRequest = { expanded },
            ) {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Lucide.EllipsisVertical,
                        contentDescription = "Menu"
                    )
                }
                DropdownMenuPanel(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(140.dp)
                ) {
                    DropdownMenuItem(onClick = {
                        navController.navigate(AppDestination.Settings.route)
                        expanded = false
                    }) { Text("Settings") }
                    DropdownMenuItem(onClick = {
                        navController.navigate(AppDestination.About.route)
                        expanded = false
                    }) { Text("About") }
                    DropdownMenuItem(onClick = {
                        navController.navigate(AppDestination.PrivacyPolicy.route)
                        expanded = false
                    }) { Text("Privacy Policy") }
                }
            }
        }
    )
}

@Preview
@Composable
fun MainTopBarPreview() {
    RxTrackerTheme {
        MainTopBar(navController = NavController(LocalContext.current))
    }
}