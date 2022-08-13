package com.example.todotasker.feature_main_screen.presentation.components.items

import android.widget.Space
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todotasker.R
import com.example.todotasker.ui.theme.*


@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    text: String,
    time: String? = null,
    date: String? = null,
    isComplete: Boolean,
    color: Color,
    onItemClick: () -> Unit = {},
) {
    Column(modifier = modifier) {

        NoteRow(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick() }
                .padding(MaterialTheme.spacings.medium),
            text = text,
            isComplete = isComplete,
            color = color,
            time = time,
            date = date
        )

        Divider(
            color = Color.Black.copy(alpha = 0.1f),
            thickness = 1.dp,
            startIndent = MaterialTheme.spacings.extraLarge
        )
    }

}



@Composable
private fun NoteRow(
    modifier: Modifier = Modifier,
    text: String,
    isComplete: Boolean,
    color: Color,
    time: String? = null,
    date: String? = null,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row (Modifier.fillMaxWidth(0.95f)){
            NoteItemIcon(
                modifier = Modifier.align(Alignment.CenterVertically),
                isComplete = isComplete
            )

            Spacer(Modifier.width(MaterialTheme.spacings.medium))

            NoteText(
                text = text,
                isComplete = isComplete,
                time = time,
                date = date
            )
        }

        CircleItem(color = color)
    }


}

@Composable
private fun NoteItemIcon(
    modifier: Modifier = Modifier,
    isComplete: Boolean,
) {
    Icon(
        modifier = modifier.size(24.dp),
        painter = painterResource(id = if (isComplete) R.drawable.ic_circle_checked else R.drawable.ic_circle),
        contentDescription = "Is complete",
        tint = if (isComplete) Blue else Gray
    )
}

@Composable
private fun NoteText(
    modifier: Modifier = Modifier,
    text: String,
    isComplete: Boolean,
    time: String? = null,
    date: String? = null,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            style = TextStyle(color = if (isComplete) Black.copy(alpha = 0.55f) else Color.Black)
        )

        Row {
            if (time != null) {
                Column {
                    Spacer(Modifier.size(8.dp))
                    ScheduleItem(
                        iconId = R.drawable.ic_alarm,
                        time = time,
                        isComplete = isComplete)
                }

                Spacer(modifier = Modifier.width(MaterialTheme.spacings.small))
            }

            if (date != null) {
                Column {
                    Spacer(Modifier.size(MaterialTheme.spacings.small))
                    ScheduleItem(
                        iconId = R.drawable.ic_shedule,
                        time = date,
                        isComplete = isComplete)
                }
            }
        }
    }
}

@Preview
@Composable
fun NoteItemPreview() {
    NoteItem(
        text = "Learn compose",
        isComplete = true,
        color = Yellow,
        onItemClick = { /*TODO*/ }
    )
}

@Preview
@Composable
fun NoteItemTimePreview() {
    NoteItem(
        text = "Learn compose",
        time = "12:01",
        isComplete = true,
        color = Green,
        onItemClick = { /*TODO*/ }
    )
}

@Preview
@Composable
fun NoteItemDatePreview() {
    NoteItem(
        text = "Learn compose",
        date = "12.12.2022",
        isComplete = false,
        color = Blue,
        onItemClick = { /*TODO*/ }
    )
}

@Preview
@Composable
fun NoteItemTimeDatePreview() {
    NoteItem(
        text = "Learn composeLearncomposeLearn composeLearn composeLearn composeLearn compose",
        time = "12:00",
        date = "12.01.01",
        isComplete = true,
        color = Green,
        onItemClick = { /*TODO*/ }
    )
}

