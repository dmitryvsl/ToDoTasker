package com.example.todotasker.feature_main_screen.presentation.components.buttons

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import com.example.todotasker.feature_main_screen.presentation.components.DropDownMenu
import com.example.todotasker.feature_main_screen.presentation.components.DropDownMenuItem
import com.example.todotasker.ui.theme.Blue
import com.example.todotasker.ui.theme.spacings


@Composable
fun FAB(
    isClicked: Boolean,
    onClick: () -> Unit,
    onDropDownDismiss: () -> Unit,
    onDropDownMenuItemClicked: (DropDownMenuItem) -> Unit,
) {
    val bgColor by animateColorAsState(if (isClicked) Blue else MaterialTheme.colors.background)
    val bgContentColor by animateColorAsState(if (isClicked) MaterialTheme.colors.background else Blue)

    Column {

        if (isClicked) {
            DropDownMenu(
                expanded = isClicked,
                onDismissRequest = onDropDownDismiss,
                onItemClick = { item ->
                    onDropDownMenuItemClicked(item)
                    Log.d("FAB", "$item clicked")
                }
            )
        }

        FloatingActionButton(
            modifier = Modifier.padding(bottom = MaterialTheme.spacings.small),
            backgroundColor = bgColor,
            contentColor = bgContentColor,
            onClick = { onClick() }
        ) {
            Icon(
                imageVector = if (isClicked) Icons.Default.Clear else Icons.Default.Add,
                contentDescription = ""
            )


        }
    }
}

@Preview("Fab")
@Composable
fun FABPreview() {
    val isClicked = remember { mutableStateOf(false) }
    FAB(
        isClicked = isClicked.value,
        onClick = { isClicked.value = !isClicked.value },
        onDropDownDismiss = { isClicked.value = !isClicked.value },
        onDropDownMenuItemClicked = {}
    )
}