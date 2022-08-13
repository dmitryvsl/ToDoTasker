package com.example.todotasker.feature_main_screen.presentation.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todotasker.R
import com.example.todotasker.ui.theme.Blue
import com.example.todotasker.ui.theme.spacings

enum class DropDownMenuItem {
    NOTE,
    TASK
}

private data class MenuItem(
    val icID: Int,
    val title: String,
)

@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onItemClick: (DropDownMenuItem) -> Unit,
) {
    val items: List<MenuItem> = remember {
        listOf(
            MenuItem(R.drawable.ic_task, "Task"),
            MenuItem(R.drawable.ic_list, "List")
        )
    }

    DropdownMenu(
        modifier = modifier
            .fillMaxWidth(0.7f),
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        offset = DpOffset(0.dp, 10.dp)
    ) {

        MenuItem(
            drawableId = items[0].icID,
            title = items[0].title,
            onClick = {
                onItemClick(DropDownMenuItem.NOTE)
                Log.d("DropDownMenu", "NOTE clicked")
            }
        )

        Divider(thickness = 1.dp, color = Color.Black.copy(alpha = 0.1f))

        MenuItem(
            drawableId = items[1].icID,
            title = items[1].title,
            onClick = {
                Log.d("DropDownMenu", "TASK clicked")
                onItemClick(DropDownMenuItem.TASK)
            }
        )

    }
}

@Composable
private fun MenuItem(
    drawableId: Int,
    title: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Log.d("MenuItem", "MenuItem clicked")
                onClick()
            }
            .padding(MaterialTheme.spacings.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = drawableId),
            contentDescription = "menu item",
            tint = Color.Blue,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            style = TextStyle(color = Blue),
            fontSize = 18.sp
        )

    }
}
