package com.example.todotasker.feature_main_screen.data.repository

import com.example.todotasker.feature_main_screen.data.database.MyDatabase
import com.example.todotasker.feature_main_screen.data.model.TaskData
import com.example.todotasker.feature_main_screen.domain.mapper.TaskToDataMapper
import com.example.todotasker.feature_main_screen.domain.mapper.TaskToDomainMapper
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.feature_main_screen.domain.repository.MutableDataSource

class TaskRepo : MutableDataSource<Task> {

    private val dao = MyDatabase.getInstance().taskDao()
    private val domainMapper = TaskToDomainMapper()
    private val dataMapper = TaskToDataMapper()


    override suspend fun getData(): List<Task> {
        val result = dao.getTasksWithNotes()
        return result.map {task ->
            domainMapper.map(task)
        }
    }

    override suspend fun saveData(data: Task) {
        dao.saveTask(dataMapper.map(data))
    }

    override suspend fun deleteData(data: Task) {
        dao.deleteTask(dataMapper.map(data))
    }
}