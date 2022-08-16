package com.example.todotasker.feature_new_note.presentation.utils

sealed class NewTaskUiEvent {
    object Initial: NewTaskUiEvent()
    object NavigateUp: NewTaskUiEvent()
    object HideKeyboard: NewTaskUiEvent()
}