package com.example.todotasker.feature_main_screen.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class MainScreenState @OptIn(ExperimentalMaterialApi::class) constructor(
    val bottomState: ModalBottomSheetState,
    val isFabClicked: MutableState<Boolean>,
) {
    fun revertIsFabClicked() {
        isFabClicked.value = !isFabClicked.value
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberMainScreenState(
    bottomState: ModalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
    isFabClicked: MutableState<Boolean> = remember { mutableStateOf(false) },
) = remember(bottomState, isFabClicked) {
    MainScreenState(bottomState, isFabClicked)
}