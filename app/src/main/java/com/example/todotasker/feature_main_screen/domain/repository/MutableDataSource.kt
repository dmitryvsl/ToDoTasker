package com.example.todotasker.feature_main_screen.domain.repository

interface MutableDataSource<T> : DataSource<T> {

    suspend fun saveData(data: T)

    suspend fun deleteData(data: T)
}