package com.example.rxtracker.uikit.components.blocks
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.composables.core.BottomSheet
import com.composables.core.DragIndication
import com.composables.core.SheetDetent.Companion.FullyExpanded
import com.composables.core.SheetDetent.Companion.Hidden
import com.composables.core.rememberBottomSheetState
import kotlinx.coroutines.delay

@Composable
fun BottomSheetsSimple() {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isCompact = maxWidth < 600.dp

        val sheetState = rememberBottomSheetState(
            initialDetent = FullyExpanded,
            detents = listOf(Hidden, FullyExpanded)
        )

        LaunchedEffect(sheetState.isIdle) {
            if (sheetState.isIdle && sheetState.currentDetent == Hidden) {
                delay(500)
                sheetState.targetDetent = FullyExpanded
            }
        }

        BottomSheet(
            state = sheetState,
            modifier = Modifier
                .padding(top = 12.dp)
                .let { if (isCompact) it else it.padding(horizontal = 56.dp) }
                .statusBarsPadding()
                .padding(WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal).asPaddingValues())
                .shadow(4.dp, RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .background(Color.White)
                .widthIn(max = 640.dp)
                .fillMaxWidth()
                .imePadding(),
        ) {
            Box(Modifier.fillMaxWidth().height(600.dp), contentAlignment = Alignment.TopCenter) {
                DragIndication(
                    modifier = Modifier.padding(top = 22.dp)
                        .background(Color.Black.copy(0.4f), RoundedCornerShape(100))
                        .width(32.dp)
                        .height(4.dp)
                )
            }
        }
    }
}
