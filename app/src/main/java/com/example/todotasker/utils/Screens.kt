package com.example.todotasker.utils

sealed class Screens{
    data class Main(val name: String = "main"): Screens()
    data class NewNote(val name: String = "new_note"): Screens()
}