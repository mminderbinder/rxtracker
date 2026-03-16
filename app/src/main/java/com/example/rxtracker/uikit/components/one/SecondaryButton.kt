package com.example.rxtracker.uikit.components.one

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.rxtracker.uikit.styling.body
import com.example.rxtracker.uikit.styling.bright
import com.example.rxtracker.uikit.styling.colors
import com.example.rxtracker.uikit.styling.dim
import com.example.rxtracker.uikit.styling.focusRing
import com.example.rxtracker.uikit.styling.indications
import com.example.rxtracker.uikit.styling.isBright
import com.example.rxtracker.uikit.styling.mutate
import com.example.rxtracker.uikit.styling.onSecondary
import com.example.rxtracker.uikit.styling.secondary
import com.example.rxtracker.uikit.styling.shapes
import com.example.rxtracker.uikit.styling.small
import com.example.rxtracker.uikit.styling.textStyles
import com.composeunstyled.ProvideTextStyle
import com.composeunstyled.focusRing
import com.composeunstyled.minimumInteractiveComponentSize
import com.composeunstyled.theme.Theme
import com.composeunstyled.Button as UnstyledButton

@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = Theme[colors][secondary],
    contentColor: Color = Theme[colors][onSecondary],
    shape: Shape = Theme[shapes][small],
    borderColor: Color = Color.Unspecified,
    borderWidth: Dp = 1.dp,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    contentPadding: PaddingValues = DefaultButtonPadding,
    content: @Composable RowScope.() -> Unit,
) {
    val overriddenBackgroundColor = backgroundColor.mutate(enabled)
    val indication = if (isBright(backgroundColor)) Theme[indications][dim] else Theme[indications][bright]

    UnstyledButton(
        enabled = enabled,
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        modifier = modifier.minimumInteractiveComponentSize()
            .focusRing(interactionSource, 2.dp, color = Theme[colors][focusRing], shape),
        shape = shape,
        onClick = onClick,
        borderColor = borderColor,
        borderWidth = borderWidth,
        backgroundColor = overriddenBackgroundColor,
        contentColor = contentColor,
        interactionSource = interactionSource,
        indication = indication,
    ) {
        ProvideTextStyle(Theme[textStyles][body].copy(fontWeight = FontWeight.Medium)) {
            content()
        }
    }
}
