package com.example.todotasker.feature_main_screen.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.feature_main_screen.presentation.components.EmptyPlaceholder
import com.example.todotasker.feature_main_screen.presentation.components.items.TaskItem
import com.example.todotasker.ui.theme.spacings

@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    taskList: List<Task>,
    onTaskClick: (Task) -> Unit,
) {

    if(taskList.isEmpty())
        EmptyPlaceholder(text = "No Lists")

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(taskList) { task ->
            TaskItem(
                title = task.title,
                noteCount = task.notes.size,
                color = Color(task.color),
                onClick = { onTaskClick(task) }
            )
        }
    }
}