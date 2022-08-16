package com.example.todotasker.feature_new_note.domain.use_cases

import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.domain.repository.MutableDataSource
import com.example.todotasker.feature_new_note.data.NoteRepo

class GetNotes {
    private val repo: MutableDataSource<Note> = NoteRepo()

    suspend operator fun invoke() = repo.getData()
}