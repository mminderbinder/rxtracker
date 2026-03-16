package com.example.rxtracker

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rxtracker.uikit.styling.RxTrackerTheme
import com.example.rxtracker.uikit.styling.colors
import com.example.rxtracker.uikit.styling.focusRing
import com.example.rxtracker.uikit.styling.onPrimary
import com.example.rxtracker.uikit.styling.outline
import com.example.rxtracker.uikit.styling.primary
import com.example.rxtracker.uikit.styling.shapes
import com.example.rxtracker.uikit.styling.small
import com.composeunstyled.Button
import com.composeunstyled.Text
import com.composeunstyled.focusRing
import com.composeunstyled.minimumInteractiveComponentSize
import com.composeunstyled.outline
import com.composeunstyled.theme.Theme

@Composable
fun PrimaryButton(onClick: () -> Unit, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = modifier
            .minimumInteractiveComponentSize()
            .outline(1.dp, Theme[colors][outline], Theme[shapes][small])
            .focusRing(interactionSource, 2.dp, Theme[colors][focusRing], Theme[shapes][small]),
        backgroundColor = Theme[colors][primary],
        contentColor = Theme[colors][onPrimary],
        shape = Theme[shapes][small],
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
    }
}

@Composable
fun GhostButton(onClick: () -> Unit, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = modifier
            .minimumInteractiveComponentSize()
            .focusRing(interactionSource, 2.dp, Theme[colors][focusRing], Theme[shapes][small]),
        backgroundColor = Color.Transparent,
        shape = Theme[shapes][small],
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    RxTrackerTheme {
        PrimaryButton(onClick = {}) {
            Text("Primary Button")
        }
    }
}

@Preview
@Composable
private fun GhostButtonPreview() {
    RxTrackerTheme {
        GhostButton(onClick = {}) {
            Text("Ghost Button")
        }
    }
}