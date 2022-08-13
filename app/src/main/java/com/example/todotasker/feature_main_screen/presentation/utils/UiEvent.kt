package com.example.todotasker.feature_main_screen.presentation.utils

import com.example.todotasker.feature_main_screen.domain.model.Task

sealed  class UiEvent(){
    object Initial: UiEvent()
    data class ShowTaskDescription(val value: Float) : UiEvent()
    data class HideTaskDescription(val value: Float): UiEvent()
    object ShowNewTaskAlert: UiEvent()
    object NavigateToNewNote: UiEvent()
}