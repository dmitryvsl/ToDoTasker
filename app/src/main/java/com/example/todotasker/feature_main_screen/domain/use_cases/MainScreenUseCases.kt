package com.example.todotasker.feature_main_screen.domain.use_cases

data class MainScreenUseCases(
    val deleteTask: DeleteTask = DeleteTask(),
    val getTasks: GetTasks = GetTasks(),
    val saveTask: SaveTask = SaveTask()
)