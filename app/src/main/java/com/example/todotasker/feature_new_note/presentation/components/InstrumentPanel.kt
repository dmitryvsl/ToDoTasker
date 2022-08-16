package com.example.todotasker.feature_new_note.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todotasker.R
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.feature_main_screen.presentation.components.items.CircleItem
import com.example.todotasker.ui.theme.Blue
import com.example.todotasker.ui.theme.spacings

@Composable
fun InstrumentPanel(
    task: Task?,
    onListClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onTimeClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CalendarInstrument(
            onCalendarClick = onCalendarClick,
            onTimeClick = onTimeClick
        )

        if (task != null)
            TaskListInstrument(onListClick = onListClick, task = task)

    }
}

@Composable
fun TaskListInstrument(
    onListClick: () -> Unit,
    task: Task,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.clickable {
                onListClick()
            },
            text = task.title,
            fontSize = 16.sp,
            style = TextStyle(color = Blue)
        )
        CircleItem(
            modifier = Modifier.padding(MaterialTheme.spacings.medium),
            color = Color(task.color)
        )

    }
}

@Composable
fun CalendarInstrument(
    onCalendarClick: () -> Unit,
    onTimeClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(start = MaterialTheme.spacings.medium)
            .padding(vertical = MaterialTheme.spacings.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable { onCalendarClick() },
            painter = painterResource(id = R.drawable.ic_shedule),
            contentDescription = null,
            tint = Blue,
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacings.medium))

        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable { onTimeClick() },
            painter = painterResource(id = R.drawable.ic_alarm),
            contentDescription = null,
            tint = Blue,
        )
    }
}