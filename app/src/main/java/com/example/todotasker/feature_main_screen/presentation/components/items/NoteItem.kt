package com.example.todotasker.feature_main_screen.presentation.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todotasker.R
import com.example.todotasker.feature_main_screen.domain.model.Note
import com.example.todotasker.ui.theme.*


@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    color: Color,
    onItemClick: () -> Unit = {},
) {
    Column(modifier = modifier) {

        NoteRow(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick() }
                .padding(MaterialTheme.spacings.medium),
            note = note,
            color = color,
        ) {
            Text(
                text = note.title,
                fontSize = 18.sp,
                style = TextStyle(color = if (note.isComplete) Black.copy(alpha = 0.55f) else Color.Black)
            )
        }

        Divider(
            color = Color.Black.copy(alpha = 0.1f),
            thickness = 1.dp,
            startIndent = MaterialTheme.spacings.extraLarge
        )
    }

}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    color: Color,
    content: @Composable () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(Modifier.fillMaxWidth(0.95f)) {
            IconItem(
                modifier = Modifier.align(Alignment.CenterVertically),
                isComplete = note.isComplete
            )

            Spacer(Modifier.width(MaterialTheme.spacings.medium))
            NoteText(
                note = note,
                content = content
            )
        }

        CircleItem(color = color)
    }


}


@Composable
private fun NoteText(
    note: Note,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        content()
        Row {
            if (note.time != null) {
                Column {
                    Spacer(Modifier.size(8.dp))
                    ScheduleItem(
                        iconId = R.drawable.ic_alarm,
                        time = note.time,
                        isComplete = note.isComplete
                    )
                }

                Spacer(modifier = Modifier.width(MaterialTheme.spacings.small))
            }

            if (note.date != null) {
                Column {
                    Spacer(Modifier.size(MaterialTheme.spacings.small))
                    ScheduleItem(
                        iconId = R.drawable.ic_shedule,
                        time = note.date,
                        isComplete = note.isComplete)
                }
            }
        }
    }
}