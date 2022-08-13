package com.example.todotasker.feature_main_screen.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todotasker.feature_main_screen.data.model.TaskAndNoteData
import com.example.todotasker.feature_main_screen.data.model.TaskData

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskData")
    fun getTasks(): List<TaskData>

    @Query("SELECT * FROM TaskData")
    fun getTasksWithNotes(): List<TaskAndNoteData>

    @Insert
    fun saveTask(task: TaskData)

    @Delete
    fun deleteTask(task: TaskData)
}