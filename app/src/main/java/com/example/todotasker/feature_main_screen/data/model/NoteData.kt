package com.example.todotasker.feature_main_screen.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = TaskData::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("taskId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NoteData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val time: String?,
    val date: String?,
    val isComplete: Boolean,
    val taskId: Int,
)