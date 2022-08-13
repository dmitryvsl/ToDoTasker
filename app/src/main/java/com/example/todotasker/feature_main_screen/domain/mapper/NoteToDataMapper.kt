package com.example.todotasker.feature_main_screen.domain.mapper

import com.example.todotasker.feature_main_screen.data.model.NoteData
import com.example.todotasker.feature_main_screen.domain.model.Note

class NoteToDataMapper : Mapper<Note, NoteData> {
    override fun map(input: Note): NoteData {
        return NoteData(
            id = input.id,
            title = input.title,
            time = input.time,
            date = input.date,
            isComplete = input.isComplete,
            taskId = input.taskId
        )
    }
}