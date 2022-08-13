package com.example.todotasker.feature_main_screen.domain.mapper

import com.example.todotasker.feature_main_screen.data.model.TaskData
import com.example.todotasker.feature_main_screen.domain.model.Task

class TaskToDataMapper : Mapper<Task, TaskData> {
    override fun map(input: Task): TaskData {
        return TaskData(
            id = input.id,
            title = input.title,
            color = input.color,
        )
    }

}