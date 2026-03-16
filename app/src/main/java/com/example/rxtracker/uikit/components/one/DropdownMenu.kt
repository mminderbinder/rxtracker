package com.example.rxtracker.uikit.components.one

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.rxtracker.uikit.styling.accent
import com.example.rxtracker.uikit.styling.bright
import com.example.rxtracker.uikit.styling.card
import com.example.rxtracker.uikit.styling.colors
import com.example.rxtracker.uikit.styling.dim
import com.example.rxtracker.uikit.styling.indications
import com.example.rxtracker.uikit.styling.isBright
import com.example.rxtracker.uikit.styling.medium
import com.example.rxtracker.uikit.styling.modal
import com.example.rxtracker.uikit.styling.onAccent
import com.example.rxtracker.uikit.styling.onCard
import com.example.rxtracker.uikit.styling.outline
import com.example.rxtracker.uikit.styling.shadows
import com.example.rxtracker.uikit.styling.shapes
import com.composeunstyled.DropdownPanelAnchor
import com.composeunstyled.LocalContentColor
import com.composeunstyled.minimumInteractiveComponentSize
import com.composeunstyled.outline
import com.composeunstyled.theme.Theme
import com.composeunstyled.Button as UnstyledButton
import com.composeunstyled.DropdownMenu as UnstyledDropdownMenu
import com.composeunstyled.DropdownMenuPanel as UnstyledDropdownMenuPanel

@Composable
fun DropdownMenu(
    onExpandRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    UnstyledDropdownMenu(modifier = modifier, onExpandRequest = onExpandRequest) {
        content()
    }
}

@Composable
fun DropdownMenuPanel(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    anchor: DropdownPanelAnchor = DropdownPanelAnchor.BottomStart,
    content: @Composable ColumnScope.() -> Unit,
) {
    UnstyledDropdownMenuPanel(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        backgroundColor = Theme[colors][card],
        contentColor = Theme[colors][onCard],
        shape = Theme[shapes][medium],
        contentPadding = PaddingValues(4.dp),
        anchor = anchor,
        modifier = modifier.padding(vertical = 4.dp)
            .dropShadow(shape = Theme[shapes][medium], shadow = Theme[shadows][modal])
            .outline(Dp.Hairline, Theme[colors][outline], Theme[shapes][medium]),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        content = content
    )
}


@Composable
fun DropdownMenuItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = LocalContentColor.current,
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val backgroundColor = if (isFocused) Theme[colors][accent] else Color.Transparent
    val contentColor = if (isFocused) Theme[colors][onAccent] else contentColor

    val indication = if (isBright(backgroundColor)) {
        Theme[indications][bright]
    } else {
        Theme[indications][dim]
    }
    UnstyledButton(
        onClick = onClick,
        shape = Theme[shapes][medium],
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        modifier = modifier.fillMaxWidth().minimumInteractiveComponentSize(),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        interactionSource = interactionSource,
        indication = indication,
    ) {
        content()
    }
}