package com.example.todotasker.feature_main_screen.domain.use_cases

import com.example.todotasker.feature_main_screen.data.repository.TaskRepo
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.feature_main_screen.domain.repository.MutableDataSource

class DeleteTask {

    private val repo: MutableDataSource<Task> = TaskRepo()

    suspend operator fun invoke(task: Task) = repo.deleteData(task)
}