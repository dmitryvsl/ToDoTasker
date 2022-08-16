@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.todotasker.feature_new_note.presentation.utils

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.todotasker.utils.Keyboard
import com.example.todotasker.utils.keyboardAsState
import kotlinx.coroutines.*

data class NewTaskState constructor(
    private val _keyboardController: SoftwareKeyboardController?,
    val keyboard: State<Keyboard>,
) {
    fun hideKeyboard() {
        _keyboardController?.hide()

    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberNewTaskState(
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    keyboard: State<Keyboard> = keyboardAsState()
) = remember( keyboardController, keyboard) {
    NewTaskState(keyboardController, keyboard)
}