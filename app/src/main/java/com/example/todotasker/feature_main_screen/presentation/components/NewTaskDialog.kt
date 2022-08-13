package com.example.todotasker.feature_main_screen.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.todotasker.feature_main_screen.domain.model.Task
import com.example.todotasker.ui.theme.colors
import com.example.todotasker.ui.theme.spacings

@Composable
fun NewTaskDialog(
    onDismissRequest: (Task) -> Unit,
) {
    val textFieldValue = remember { mutableStateOf("") }
    val selectedColor = remember { mutableStateOf(colors[0]) }
    val task = Task(
        id = 0,
        title = "",
        color = selectedColor.value.toArgb(),
        notes = emptyList())

    AlertDialog(
        onDismissRequest = {
            onDismissRequest(
                task.copy(title = textFieldValue.value)
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "New Task",
                    style = TextStyle(color = selectedColor.value),
                    fontSize = 18.sp
                )
                Spacer(Modifier.height(MaterialTheme.spacings.medium))

                CustomTextField(
                    textFieldValue = textFieldValue.value,
                    onValueChange = { value -> textFieldValue.value = value },
                    onDone = {
                        onDismissRequest(
                            task.copy(title = textFieldValue.value)
                        )
                    }
                )

                Spacer(Modifier.height(MaterialTheme.spacings.medium))

                ColorPicker(
                    colors = colors,
                    selectedColor = selectedColor.value,
                    onColorPick = { color -> selectedColor.value = color }
                )
            }
        },
        buttons = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacings.extraLarge),
                colors = ButtonDefaults.buttonColors(backgroundColor = selectedColor.value),
                onClick = {
                    onDismissRequest(task.copy(title = textFieldValue.value))
                }
            ) {
                Text(
                    text = "Save",
                    style = TextStyle(color = Color.White)
                )
            }
        }
    )
}
