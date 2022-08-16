package com.example.todotasker.feature_new_note.presentation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.feature_main_screen.presentation.TaskList
import com.example.todotasker.feature_new_note.presentation.components.CustomTimePicker
import com.example.todotasker.feature_new_note.presentation.utils.InstrumentState
import com.example.todotasker.ui.theme.spacings

@Composable
fun InstrumentPanelExpanded(
    modifier: Modifier = Modifier,
    instrumentState: InstrumentState,
    taskList: List<Task>,
    onTaskClick: (Task) -> Unit,
    onTimeSelected: (String) -> Unit,
) {
    when (instrumentState) {
        InstrumentState.Hidden -> {}
        InstrumentState.Calendar -> {
            /* TODO Добавить календарь */
        }
        InstrumentState.TimePicker ->
            CustomTimePicker { onTimeSelected(it) }
        InstrumentState.TaskList -> TaskList(
            modifier = modifier
                .height(400.dp)
                .padding(horizontal = MaterialTheme.spacings.medium),
            taskList = taskList,
            onTaskClick = { onTaskClick(it) }
        )
        InstrumentState.Keyboard -> {}
    }
}