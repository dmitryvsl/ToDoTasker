package com.example.todotasker.feature_main_screen.domain.mapper

import com.example.todotasker.feature_main_screen.data.model.TaskAndNoteData
import com.example.todotasker.feature_main_screen.domain.model.Task

class TaskToDomainMapper : Mapper<TaskAndNoteData, Task> {
    override fun map(input: TaskAndNoteData): Task {
        val mapper = NoteToDomainMapper()
        return Task(
            id = input.task.id,
            title = input.task.title,
            color = input.task.color,
            notes = input.notes.map { note -> mapper.map(note) }
        )
    }
}