package com.example.todotasker.feature_main_screen.domain.repository

interface DataSource<T> {

    suspend fun getData(): List<T>
}
