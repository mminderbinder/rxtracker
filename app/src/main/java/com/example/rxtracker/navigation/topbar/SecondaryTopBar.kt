package com.example.rxtracker.navigation.topbar

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.Lucide
import com.composeunstyled.theme.Theme
import com.example.rxtracker.uikit.components.one.Icon
import com.example.rxtracker.uikit.components.one.IconButton
import com.example.rxtracker.uikit.components.one.Text
import com.example.rxtracker.uikit.components.one.TopAppBar
import com.example.rxtracker.uikit.styling.RxTrackerTheme
import com.example.rxtracker.uikit.styling.colors
import com.example.rxtracker.uikit.styling.navigation

@Composable
fun SecondaryTopBar(
    title: String,
    navController: NavController
) {
    val darkTheme = isSystemInDarkTheme()
    TopAppBar(
        backgroundColor = if (darkTheme) Theme[colors][navigation] else Color(0xFF2563EB),
        contentColor = if (darkTheme) Theme[colors][navigation] else Color.White,
        title = {Text(title)},
        navigation = {
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(
                    imageVector = Lucide.ArrowLeft,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Preview
@Composable
fun SecondaryTopBarPreview() {
    RxTrackerTheme {
        SecondaryTopBar(
            title = "Title",
            navController = NavController(LocalContext.current)
        )
    }
}