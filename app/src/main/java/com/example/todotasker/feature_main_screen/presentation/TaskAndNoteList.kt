package com.example.todotasker.feature_main_screen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.domain.model.Task

@Composable
fun TaskAndNoteList(
    modifier: Modifier = Modifier,
    colorMap: Map<Int, Int>,
    noteList: List<Note>,
    taskList: List<Task>
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        NoteList(
            colors = colorMap,
            notes = noteList
        )
    }
}