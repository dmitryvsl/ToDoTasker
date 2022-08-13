package com.example.todotasker.feature_main_screen.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todotasker.feature_main_screen.data.model.NoteData
import com.example.todotasker.feature_main_screen.data.model.TaskData

@Database(entities = [NoteData::class, TaskData::class], version = 1)
abstract class ToDoDatabase : RoomDatabase(){

    abstract fun taskDao(): TaskDao

    abstract fun noteDao(): NoteDao
}