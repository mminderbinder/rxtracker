package com.example.rxtracker.ui.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composeunstyled.theme.Theme
import com.example.rxtracker.uikit.components.one.Text
import com.example.rxtracker.uikit.styling.RxTrackerTheme
import com.example.rxtracker.uikit.styling.textStyles
import com.example.rxtracker.uikit.styling.title

@Composable
fun HistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Intake History",
            style = Theme[textStyles][title]
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    RxTrackerTheme {
        HistoryScreen()
    }
}