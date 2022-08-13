package com.example.todotasker.feature_main_screen.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.feature_main_screen.presentation.components.NewTaskDialog
import com.example.todotasker.feature_main_screen.presentation.components.lists.NoteList
import com.example.todotasker.ui.theme.Black
import com.example.todotasker.ui.theme.spacings

@Composable
fun TaskAndNoteList(
    modifier: Modifier = Modifier,
    colorMap: Map<Int, Int>,
    noteList: List<Note>,
    taskList: List<Task>,
    onTaskClick: (Task) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        NoteList(
            colors = colorMap,
            notes = noteList
        )

        TaskHeader()

        TaskList(
            taskList = taskList,
            onTaskClick = { task -> onTaskClick(task) }
        )

    }
}

@Composable
fun TaskHeader() {
    Text(
        modifier = Modifier
            .padding(
                start = MaterialTheme.spacings.extraLarge,
                top = MaterialTheme.spacings.large,
                bottom = MaterialTheme.spacings.medium,
            )
            .fillMaxWidth(),
        text = "Lists",
        style = TextStyle(color = Black.copy(alpha = 0.3f)),
        fontSize = 16.sp,
    )
}
