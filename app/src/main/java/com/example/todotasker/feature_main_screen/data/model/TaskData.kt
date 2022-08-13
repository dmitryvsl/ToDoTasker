package com.example.todotasker.feature_main_screen.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val color: Int,
)