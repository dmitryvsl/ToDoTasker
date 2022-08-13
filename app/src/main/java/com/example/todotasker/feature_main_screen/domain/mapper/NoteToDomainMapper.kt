package com.example.todotasker.feature_main_screen.domain.mapper

import com.example.todotasker.feature_main_screen.data.model.NoteData
import com.example.todotasker.feature_main_screen.domain.model.Note

class NoteToDomainMapper : Mapper<NoteData, Note> {
    override fun map(input: NoteData): Note {
        return Note(
            id = input.id,
            title = input.title,
            time = input.time,
            date = input.date,
            isComplete = input.isComplete,
            taskId = input.taskId
        )
    }
}