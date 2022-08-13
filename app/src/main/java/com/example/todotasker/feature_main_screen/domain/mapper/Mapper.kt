package com.example.todotasker.feature_main_screen.domain.mapper

interface Mapper<in I, out O> {

    fun map(input: I): O
}