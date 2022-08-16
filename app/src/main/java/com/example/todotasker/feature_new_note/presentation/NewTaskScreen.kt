package com.example.todotasker.feature_new_note.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.feature_new_note.presentation.components.ActionBar
import com.example.todotasker.feature_new_note.presentation.components.InstrumentPanel
import com.example.todotasker.feature_new_note.presentation.components.NoteTextField
import com.example.todotasker.feature_new_note.presentation.utils.InstrumentState
import com.example.todotasker.feature_new_note.presentation.utils.NewTaskState
import com.example.todotasker.feature_new_note.presentation.utils.NewTaskUiEvent
import com.example.todotasker.feature_new_note.presentation.utils.rememberNewTaskState
import com.example.todotasker.ui.theme.Black
import com.example.todotasker.utils.Keyboard

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NewTaskScreen(
    navController: NavController,
) {
    val viewModel: NewTaskViewModel = viewModel()

    val uiEvents = viewModel.eventFlow.collectAsState(initial = NewTaskUiEvent.Initial)
    val taskList = viewModel.taskList.observeAsState()
    val note = viewModel.note.observeAsState()
    val instrumentState = viewModel.instrumentState.observeAsState()
    val uiState = rememberNewTaskState()

    LaunchedEffect(uiState.keyboard.value) {
        when (uiState.keyboard.value) {
            Keyboard.Opened -> viewModel.handleInstrumentState(InstrumentState.Keyboard)
            Keyboard.Closed -> {}
        }
    }
    ProcessEvents(navController = navController, uiState = uiState, uiEvent = uiEvents.value)
    Scaffold(
        topBar = {
            ActionBar(
                onCancelClick = { viewModel.onCancelClicked() },
                onDoneClick = {
                    viewModel.onDoneClicked()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            NoteTextField(
                note = note.value ?: Note(0, "", null, null, false, 0),
                color = Color(
                    findColorByTaskId(taskList.value ?: emptyList(), note.value?.taskId ?: -1)
                ),
                onValueChange = { viewModel.onTextChanged(it) },
                onDone = {
                    viewModel.onDoneClicked()
                }
            )

            Column {
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Black.copy(alpha = 0.1f)
                )

                InstrumentPanel(
                    task = taskList.value?.find { it.id == note.value?.taskId },
                    onListClick = {
                        viewModel.handleInstrumentState(InstrumentState.TaskList)
                    },
                    onCalendarClick = {
                        viewModel.handleInstrumentState(InstrumentState.Calendar)
                    },
                    onTimeClick = {
                        viewModel.handleInstrumentState(InstrumentState.TimePicker)
                    }
                )

                InstrumentPanelExpanded(
                    instrumentState = instrumentState.value ?: InstrumentState.Hidden,
                    taskList = taskList.value ?: emptyList(),
                    onTaskClick = { task ->
                        viewModel.onTaskClicked(task)
                    },
                    onTimeSelected = { viewModel.onTimeSelected(it) }
                )
            }

        }
    }
}


@Composable
private fun ProcessEvents(
    navController: NavController,
    uiState: NewTaskState,
    uiEvent: NewTaskUiEvent,
) {
    when (uiEvent) {
        NewTaskUiEvent.Initial -> {}
        NewTaskUiEvent.NavigateUp -> navController.navigateUp()
        NewTaskUiEvent.HideKeyboard -> uiState.hideKeyboard()
    }
}

private fun findColorByTaskId(taskList: List<Task>, id: Int) =
    taskList.find { it.id == id }?.color ?: White.toArgb()

