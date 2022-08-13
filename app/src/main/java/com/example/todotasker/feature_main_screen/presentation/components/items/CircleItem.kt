package com.example.todotasker.feature_main_screen.presentation.components.items

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircleItem(
    modifier: Modifier = Modifier,
    color: Color
){
    Canvas(
        modifier = modifier
            .size(12.dp)
    ) {
        drawCircle(color = color)
    }
}