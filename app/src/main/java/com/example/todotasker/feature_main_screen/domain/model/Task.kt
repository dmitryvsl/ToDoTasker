package com.example.todotasker.feature_main_screen.domain.model

data class Task(
    val id: Int,
    val title: String,
    val color: Int,
    val notes: List<Note>,
)