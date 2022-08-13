package com.example.todotasker.feature_main_screen.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.todotasker.ui.theme.Black

@Composable
fun EmptyPlaceholder(
    modifier: Modifier = Modifier,
    text: String
){
    Text(
        modifier = modifier,
        text = text,
        fontSize = 32.sp,
        style = TextStyle(color = Black)
    )
}