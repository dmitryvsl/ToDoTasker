package com.example.todotasker.feature_main_screen.presentation.components.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todotasker.R
import com.example.todotasker.ui.theme.Black


@Composable
fun ScheduleItem(
    iconId: Int,
    time: String,
    isComplete: Boolean
) {
    val color = Black.copy(alpha = if (isComplete) 0.15f else 0.3f)
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(end = 4.dp),
            painter = painterResource(id = iconId),
            contentDescription = "schedule Element",
            tint = color
        )
        Text(
            text = time,
            style = TextStyle(color = color),
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun ScheduleItemPreview(){
    ScheduleItem(
        iconId = R.drawable.ic_alarm,
        time = "12:00",
        isComplete = false
    )
}
