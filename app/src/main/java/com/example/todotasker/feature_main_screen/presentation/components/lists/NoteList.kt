package com.example.todotasker.feature_main_screen.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.presentation.components.items.NoteItem
import com.example.todotasker.ui.theme.Blue
import com.example.todotasker.ui.theme.Yellow
import kotlin.random.Random

@Composable
fun NoteList(
    colors: Map<Int, Int>,
    notes: List<Note>,
) {
    Log.d("NoteList", "list count: ${notes.size}")
    LazyColumn (
        modifier = Modifier.fillMaxSize()
            ) {
        items(notes) { note ->
            NoteItem(
                text = note.title,
                time = note.time,
                isComplete = note.isComplete,
                color = Color(colors[note.taskId]!!),
                onItemClick = { /*TODO*/ }
            )
        }
    }
}

@Preview("NoteList")
@Composable
fun NoteListPreview() {
    val notes = mutableListOf<Note>()
    for (i in 0..15) {
        notes.add(
            Note(
                id = i,
                title = "Test $i",
                isComplete = i % 3 == 0,
                time = if (i % 2 == 0) "12:00" else null,
                date = if (i % 5 == 0) "12.02.2020" else null,
                taskId = Random.nextInt(1, 2)
            )
        )
    }
    notes.add(
        Note(
            id = 16,
            title = "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test ",
            isComplete = false,
            time = "12:00",
            date = "12.02.2020",
            taskId = Random.nextInt(1, 2)
        )
    )
    NoteList(
        colors = mapOf(1 to Yellow.toArgb(), 2 to Blue.toArgb()),
        notes = notes
    )
}
