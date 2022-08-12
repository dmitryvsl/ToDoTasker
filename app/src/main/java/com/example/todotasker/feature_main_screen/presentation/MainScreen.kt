package com.example.todotasker.feature_main_screen.presentation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.presentation.components.TopBar
import com.example.todotasker.feature_main_screen.presentation.components.buttons.FAB

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
    val mainScreenState = rememberMainScreenState()

    val viewModel: MainScreenViewModel = viewModel()

    val taskList = viewModel.tasks.observeAsState()
    val noteList = viewModel.notes.observeAsState()
    val colorMap = viewModel.colors.observeAsState()

    ModalBottomSheetLayout(
        sheetState = mainScreenState.bottomState,
        sheetContent = {Text("1")},
    ) {
        MyScaffold(
            screenState = mainScreenState,
            colorMap = colorMap.value ?: emptyMap(),
            taskList = taskList.value ?: emptyList(),
            noteList = noteList.value ?: emptyList()
        )
    }
}



