package com.example.todotasker.feature_new_note.data

import com.example.todotasker.feature_main_screen.data.database.MyDatabase
import com.example.todotasker.feature_main_screen.domain.mapper.NoteToDataMapper
import com.example.todotasker.feature_main_screen.domain.mapper.NoteToDomainMapper
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.domain.repository.DataSource
import com.example.todotasker.feature_main_screen.domain.repository.MutableDataSource

class NoteRepo : MutableDataSource<Note> {
    private val dao = MyDatabase.getInstance().noteDao()
    private val domainMapper = NoteToDomainMapper()
    private val dataMapper = NoteToDataMapper()

    override suspend fun getData(): List<Note> {
        val result = dao.getNotes()
        return result.map { note -> domainMapper.map(note) }
    }

    override suspend fun saveData(data: Note) {
        dao.saveNote(dataMapper.map(data))
    }

    override suspend fun deleteData(data: Note) {
        TODO("Not yet implemented")
    }
}