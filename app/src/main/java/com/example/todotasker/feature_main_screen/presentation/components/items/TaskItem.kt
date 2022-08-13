package com.example.todotasker.feature_main_screen.presentation.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.todotasker.ui.theme.Black
import com.example.todotasker.ui.theme.spacings


@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    title: String,
    noteCount: Int,
    color: Color,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .zIndex(0f)
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacings.extraSmall),
        shape = RoundedCornerShape(10.dp),
        color = color
    ) {
        Column(
            modifier = Modifier
                .clickable { onClick() }
                .padding(
                    vertical = MaterialTheme.spacings.medium,
                    horizontal = MaterialTheme.spacings.medium
                )
        ) {
            Text(
                text = title,
                style = TextStyle(color = Black),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$noteCount task" + if (noteCount > 1) "s" else "",
                style = TextStyle(color = Black.copy(alpha = 0.5f))
            )
        }
    }
}
