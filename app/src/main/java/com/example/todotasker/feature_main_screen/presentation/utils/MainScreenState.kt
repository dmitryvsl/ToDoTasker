package com.example.todotasker.feature_main_screen.presentation.utils

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.todotasker.feature_main_screen.domain.model.Task

class MainScreenState @OptIn(ExperimentalMaterialApi::class) constructor(
    val bottomState: ModalBottomSheetState,
    val isFabClicked: MutableState<Boolean>,
    val isOpenDialog: MutableState<Boolean>,
    val emptyTask: Task = Task(0, "", 1, emptyList()),
) {
    fun revertIsFabClicked() {
        isFabClicked.value = !isFabClicked.value
    }

    fun revertIsOpenDialog() {
        isOpenDialog.value = !isOpenDialog.value
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberMainScreenState(
    bottomState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
    isFabClicked: MutableState<Boolean> = remember { mutableStateOf(false) },
    isOpenDialog: MutableState<Boolean> = remember { mutableStateOf(false) },
) = remember(bottomState, isFabClicked, isOpenDialog) {
    MainScreenState(
        bottomState = bottomState,
        isFabClicked = isFabClicked,
        isOpenDialog
    )
}