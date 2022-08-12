package com.example.todotasker.feature_main_screen.domain.model

data class Note(
    val id: Int,
    val title: String,
    val time: String? = null,
    val date: String? = null,
    val isComplete: Boolean,
    val taskId: Int
)