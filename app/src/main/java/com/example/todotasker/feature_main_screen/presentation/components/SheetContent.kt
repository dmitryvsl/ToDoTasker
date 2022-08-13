package com.example.todotasker.feature_main_screen.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.feature_main_screen.presentation.components.items.NoteItem
import com.example.todotasker.ui.theme.Black
import com.example.todotasker.ui.theme.spacings

@Composable
fun SheetContent(
    task: Task,
    onRemoveTaskClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth()
    ) {
        TaskDescriptionHeader(
            task = task,
            onRemoveTaskClick = onRemoveTaskClick
        )

        TasksNoteList(task = task)
    }
}


@Composable
private fun TaskDescriptionHeader(
    task: Task,
    onRemoveTaskClick: () -> Unit,
) {
    Column {
        Divider(
            modifier = Modifier
                .width(40.dp)
                .padding(top = MaterialTheme.spacings.small)
                .align(Alignment.CenterHorizontally),
            thickness = 5.dp,
            color = Black.copy(alpha = 0.2f)
        )
        Spacer(Modifier.height(16.dp))
        TaskHeaderRow(
            task = task,
            onRemoveTaskClick = onRemoveTaskClick
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
private fun TaskHeaderRow(
    task: Task,
    onRemoveTaskClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(
                start = MaterialTheme.spacings.extraLarge,
                end = MaterialTheme.spacings.medium
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = task.title,
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${task.notes.size} task" + if (task.notes.size > 1) "s" else "",
                style = TextStyle(color = Color.Black.copy(alpha = 0.5f)),
                fontSize = 16.sp
            )
        }

        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onRemoveTaskClick
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun TasksNoteList(
    task: Task,
) {
    LazyColumn {
        items(task.notes) { note ->
            NoteItem(
                text = note.title,
                time = note.time,
                isComplete = note.isComplete,
                color = Color(task.color)
            )
        }
    }
}