package com.example.todotasker.feature_main_screen.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todotasker.feature_main_screen.data.model.NoteData

@Dao
interface NoteDao {

    @Query("SELECT * FROm NoteData")
    fun getNotes(): List<NoteData>

    @Insert
    fun saveNote(noteData: NoteData)

}