package com.example.todotasker.feature_main_screen.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todotasker.feature_main_screen.presentation.components.items.CircleItem
import com.example.todotasker.ui.theme.spacings

@Composable
fun ColorPicker(
    colors: List<Color>,
    selectedColor: Color,
    onColorPick: (Color) -> Unit,
) {
    LazyRow {
        items(colors) { color ->
//            Button(
//                modifier = Modifier,
//                onClick = { onColorPick(color) },
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = color,
//                    contentColor = Color.White
//                ),
//                shape = CircleShape
//            ) {
//                if (selectedColor == color)
//                    Icon(
//                        modifier = Modifier.size(12.dp),
//                        imageVector = Icons.Default.Done,
//                        contentDescription = "",
//                        tint = Color.White
//                    )
//            }
            CircleItem(
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacings.small)
                    .clickable {
                        onColorPick(color)
                    },
                color = color
            )
        }
    }
}

//@Preview
//@Composable
//fun Preview() {
//    val color = remember{ mutableStateOf(colors[1])}
//    ColorPicker(
//        colors,
//        color.value
//    ) {color.value = it}
//}