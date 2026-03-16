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
import androidx.compose.ui.graphics.isUnspecified
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
import com.example.rxtracker.uikit.styling.shapes
import com.example.rxtracker.uikit.styling.small
import com.example.rxtracker.uikit.styling.textStyles
import com.composeunstyled.LocalContentColor
import com.composeunstyled.ProvideTextStyle
import com.composeunstyled.focusRing
import com.composeunstyled.minimumInteractiveComponentSize
import com.composeunstyled.theme.Theme
import com.composeunstyled.Button as UnstyledButton

@Composable
fun GhostButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    shape: Shape = Theme[shapes][small],
    contentColor: Color = LocalContentColor.current,
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 1.dp,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    contentPadding: PaddingValues = DefaultButtonPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    val overriddenContentColor = contentColor.mutate(enabled)
    val overriddenBackgroundColor = backgroundColor.mutate(enabled)
    val borderColor = when {
        borderColor.isUnspecified || borderColor == Color.Transparent -> Color.Transparent
        enabled -> borderColor
        else -> borderColor.copy(alpha = 0.50f)
    }
    val indication = if (isBright(overriddenBackgroundColor)) Theme[indications][bright] else Theme[indications][dim]

    UnstyledButton(
        onClick = onClick,
        shape = shape,
        backgroundColor = overriddenBackgroundColor,
        modifier = modifier.minimumInteractiveComponentSize()
            .focusRing(interactionSource, 2.dp, color = Theme[colors][focusRing], shape),
        enabled = enabled,
        borderColor = borderColor,
        horizontalArrangement = horizontalArrangement,
        borderWidth = borderWidth,
        contentColor = overriddenContentColor,
        contentPadding = contentPadding,
        verticalAlignment = verticalAlignment,
        interactionSource = interactionSource,
        indication = indication,
    ) {
        ProvideTextStyle(Theme[textStyles][body].copy(fontWeight = FontWeight.Medium)) {
            content()
        }
    }
}

