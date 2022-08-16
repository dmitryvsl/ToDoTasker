package com.example.todotasker.feature_new_note.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todotasker.ui.theme.Black
import com.example.todotasker.ui.theme.spacings

@Composable
fun CustomTimePicker(
    modifier: Modifier = Modifier,
    onTimeSelected: (String) -> Unit,
) {
    val height = calculatePanelHeight()
    val hour = remember { mutableStateOf("") }
    val minute = remember { mutableStateOf("") }

    LaunchedEffect(hour.value, minute.value) {
            onTimeSelected("${hour.value}:${minute.value}")
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.Center
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 44.dp),
            thickness = 1.dp,
            color = Black.copy(0.1f),
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 44.dp),
            thickness = 1.dp,
            color = Black.copy(0.1f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            HourColumn { hour.value = it }
            Spacer(modifier = Modifier.width(MaterialTheme.spacings.medium))
            MinuteColumn { minute.value = it }
        }
    }
}

@Composable
private fun HourColumn(
    onHourSelected: (String) -> Unit,
) {
    val hours = createList(24)

    val listState = rememberLazyListState()
    val firstIndex = remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val firstOffset = remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    val isScrolling = remember { derivedStateOf { listState.isScrollInProgress } }

    LaunchedEffect(firstIndex.value, firstOffset.value, isScrolling.value) {
        if (!isScrolling.value) {
            listState.animateScrollToItem(firstIndex.value, -firstOffset.value)
            onHourSelected(hours[firstIndex.value + 2])
        }
    }

    LazyColumn(
        modifier = Modifier.width(MaterialTheme.spacings.extraLarge),
        state = listState,
    ) {
        itemsIndexed(hours) { index, hour ->
            val color: Color = getColorByIndex(firstIndex.value, index)
            val fontSize = getFontSizeByIndex(firstIndex.value, index)

            Text(
                modifier = Modifier.padding(MaterialTheme.spacings.small),
                text = hour,
                fontSize = fontSize,
                fontWeight = FontWeight.Normal,
                style = TextStyle(color = color)
            )
        }
    }
}

@Composable
private fun MinuteColumn(
    onMinuteSelected: (String) -> Unit,
) {
    val minutes = createList(60)

    val listState = rememberLazyListState()
    val firstIndex = remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val firstOffset = remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    val isScrolling = remember { derivedStateOf { listState.isScrollInProgress } }

    LaunchedEffect(firstIndex.value, firstOffset.value, isScrolling.value) {
        if (!isScrolling.value) {
            listState.animateScrollToItem(firstIndex.value, -firstOffset.value)
            //2 пустых элемента, добавленные в начало списка
            onMinuteSelected(minutes[firstIndex.value + 2]) //
        }
    }

    LazyColumn(
        modifier = Modifier.width(MaterialTheme.spacings.extraLarge),
        state = listState,
    ) {
        itemsIndexed(minutes) { index, minute ->
            val color: Color = getColorByIndex(firstIndex.value, index)
            val fontSize: TextUnit = getFontSizeByIndex(firstIndex.value, index)
            Text(
                modifier = Modifier.padding(vertical = MaterialTheme.spacings.small),
                text = minute,
                fontSize = fontSize,
                fontWeight = FontWeight.Normal,
                style = TextStyle(
                    color = color
                )
            )
        }
    }
}

private fun getColorByIndex(firstIndex: Int, currentIndex: Int) = when (currentIndex) {
    firstIndex, firstIndex + 4 -> Black.copy(alpha = 0.3f)
    firstIndex + 1, firstIndex + 3 -> Black.copy(alpha = 0.7f)
    firstIndex + 2 -> Black
    else -> Black.copy(0.1f)
}

private fun getFontSizeByIndex(firstIndex: Int, currentIndex: Int) = when (currentIndex) {
    firstIndex, firstIndex + 4 -> 20.sp
    firstIndex + 1, firstIndex + 3 -> 22.sp
    firstIndex + 2 -> 24.sp
    else -> 18.sp
}

@Composable
private fun calculatePanelHeight(): Dp {
    // Конвертируем размер текста в dp
    val spInDp = with(LocalDensity.current) {
        (24.sp).toDp()
    }
    val verticalPaddings = MaterialTheme.spacings.small

    // размер текста 20-24 sp
    // 8 dp на вертикальные паддинги
    // 8 * 2 + 24 = ~40 dp на 1  элемент
    // Необходимо вместить 5 элементов
    // 40 * 5 = 200 dp
    // Итого высота панели 200dp
    // + 24 dp сверху накинул тупо методом тыка
    // Скорее всего LazyColumn между итемами паддинги ставит 12 dp
    return remember { (spInDp + verticalPaddings * 2) * 5 + 24.dp }
}

private fun createList(count: Int): List<String> {
    val values = MutableList(count) { number ->
        val item = if (number in 0..9) "0$number"
        else number.toString()
        item
    }
    values.add(0, "")
    values.add(0, "")
    values.add("")
    values.add("")
    values.add("")
    return values
}
