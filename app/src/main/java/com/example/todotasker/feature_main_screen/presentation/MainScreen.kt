package com.example.todotasker.feature_main_screen.presentation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todotasker.feature_main_screen.presentation.components.SheetContent
import com.example.todotasker.feature_main_screen.presentation.utils.MainScreenState
import com.example.todotasker.feature_main_screen.presentation.utils.UiEvent
import com.example.todotasker.feature_main_screen.presentation.utils.rememberMainScreenState
import com.example.todotasker.utils.Screens

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(navController: NavController) {
    val mainScreenState = rememberMainScreenState()

    val viewModel: MainScreenViewModel = viewModel()

    val taskList = viewModel.tasks.observeAsState()
    val noteList = viewModel.notes.observeAsState()
    val colorMap = viewModel.colors.observeAsState()
    val selectedTask = viewModel.selectedTask.observeAsState()
    val uiEvent = viewModel.eventsFlow.collectAsState(initial = UiEvent.Initial)

    LaunchedEffect(navController.currentBackStackEntry?.id.toString()) {
        viewModel.getData()
    }

    ProcessUiEvents(
        screenState = mainScreenState,
        navController = navController,
        uiEvent = uiEvent.value,
    )

    ModalBottomSheetLayout(
        sheetState = mainScreenState.bottomState,
        sheetContent = {
            SheetContent(
                task = selectedTask.value ?: mainScreenState.emptyTask,
                onRemoveTaskClick = { viewModel.removeTaskClicked() }
            )
        },
        sheetShape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp),
        sheetBackgroundColor = Color(selectedTask.value?.color ?: mainScreenState.emptyTask.color)
    ) {
        MyScaffold(
            scaffoldState = mainScreenState.scaffoldState,
            screenState = mainScreenState,
            colorMap = colorMap.value ?: emptyMap(),
            taskList = taskList.value ?: emptyList(),
            noteList = noteList.value ?: emptyList(),
            onTaskClick = { task -> viewModel.taskClicked(task) },
            onNewTaskClick = {
                viewModel.newTaskClicked()
                mainScreenState.revertIsFabClicked()
                mainScreenState.revertIsOpenDialog()
            },
            onNewTaskDismissClick = { task ->
                viewModel.saveNewTask(task)
                mainScreenState.revertIsOpenDialog()
            },
            onNewNoteClick = {
                mainScreenState.revertIsFabClicked()
                viewModel.newNoteClicked()
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProcessUiEvents(
    screenState: MainScreenState,
    navController: NavController,
    uiEvent: UiEvent,
) {
    when (uiEvent) {
        UiEvent.Initial -> {}
        is UiEvent.ShowTaskDescription ->
            LaunchedEffect(uiEvent.value) {
                screenState.bottomState.animateTo(ModalBottomSheetValue.Expanded)
            }

        is UiEvent.HideTaskDescription -> {
            LaunchedEffect(uiEvent.value) {
                screenState.bottomState.animateTo(ModalBottomSheetValue.Hidden)
            }
        }

        UiEvent.ShowNewTaskAlert -> screenState.isOpenDialog.value = true

        UiEvent.NavigateToNewNote -> navController.navigate(Screens.NewNote().name) {
            launchSingleTop = true
        }
        is UiEvent.ShowSnackbar -> {
            LaunchedEffect(uiEvent.message) {
                screenState.scaffoldState.snackbarHostState.showSnackbar(
                    message = uiEvent.message,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
}