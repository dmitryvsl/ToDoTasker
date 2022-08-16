package com.example.todotasker.feature_main_screen.presentation.components.items

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todotasker.R
import com.example.todotasker.ui.theme.Black
import com.example.todotasker.ui.theme.Blue
import com.example.todotasker.ui.theme.Gray

@Composable
fun IconItem(
    modifier: Modifier = Modifier,
    isComplete: Boolean = false,
) {
    Icon(
        modifier = modifier.size(24.dp),
        painter = painterResource(id = if (isComplete) R.drawable.ic_circle_checked else R.drawable.ic_circle),
        contentDescription = "Is complete",
        tint = if (isComplete) Blue else Black
    )
}