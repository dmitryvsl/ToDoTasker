package com.example.todotasker.feature_main_screen.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import com.example.todotasker.ui.theme.Gray
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun Overlay(
    showOverlay: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    OverlaySystemUi(getOverlayColor(showOverlay))
    Box(
        modifier = modifier
            .drawBehind {
                drawRect(getOverlayColor(showOverlay))
            }
    ) {
        content()
    }
}

@Composable
private fun OverlaySystemUi(
    color: Color,
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = color,
            darkIcons = useDarkIcons
        )
    }
}

private fun getOverlayColor(showOverlay: Boolean): Color =
    Gray.copy(alpha = if (showOverlay) 0.4f else 0f)