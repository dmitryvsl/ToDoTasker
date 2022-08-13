package com.example.todotasker.feature_main_screen.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class TaskAndNoteData(
    @Embedded val task: TaskData,
    @Relation(
        parentColumn = "id",
        entityColumn = "taskId"
    )
    val notes: List<NoteData>,
)