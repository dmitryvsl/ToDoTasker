package com.example.todotasker.feature_new_note.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.feature_main_screen.presentation.components.items.CircleItem
import com.example.todotasker.feature_main_screen.presentation.components.items.IconItem
import com.example.todotasker.feature_main_screen.presentation.components.items.NoteRow
import com.example.todotasker.ui.theme.Black
import com.example.todotasker.ui.theme.spacings

@Composable
fun NoteTextField(
    note: Note,
    onValueChange: (String) -> Unit,
    color: Color,
    onDone: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    NoteRow(
        modifier = Modifier.padding(MaterialTheme.spacings.medium),
        color = color,
        note = note
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(end = MaterialTheme.spacings.large)
                .focusRequester(focusRequester),
            value = note.title,
            onValueChange = { onValueChange(it) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                textColor = Black
            ),
            placeholder = {
                Text(text = "What do you want to do?")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { onDone() })
        )
    }
}
