package com.example.todotasker.feature_main_screen.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.feature_main_screen.presentation.components.Overlay
import com.example.todotasker.feature_main_screen.presentation.components.TopBar
import com.example.todotasker.feature_main_screen.presentation.components.buttons.FAB

const val TAG = "MyScaffold"

@Composable
fun MyScaffold(
    screenState: MainScreenState,
    colorMap: Map<Int, Int>,
    taskList: List<Task>,
    noteList: List<Note>,
) {
    Scaffold(
        topBar = {
            Overlay(
                modifier = Modifier.fillMaxWidth(),
                showOverlay = screenState.isFabClicked.value
            ){
                TopBar() {}
            }
        },
        floatingActionButton = {
            FAB(
                screenState.isFabClicked.value,
                onClick = { screenState.revertIsFabClicked() },
                onDropDownDismiss = { screenState.revertIsFabClicked() },
                onDropDownMenuItemClicked = { item ->
                    Log.d(TAG, "$item clicked!")
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Overlay(
            modifier = Modifier.fillMaxSize(),
            showOverlay = screenState.isFabClicked.value
        ) {
            TaskAndNoteList(
                modifier = Modifier.padding(paddingValues),
                taskList = taskList,
                noteList = noteList,
                colorMap = colorMap
            )
        }
    }
}
