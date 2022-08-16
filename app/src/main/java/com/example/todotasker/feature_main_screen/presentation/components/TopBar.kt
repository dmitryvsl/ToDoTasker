package com.example.todotasker.feature_main_screen.presentation.components

import com.example.todotasker.R
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todotasker.ui.theme.Blue
import com.example.todotasker.ui.theme.spacings


@Composable
fun TopBar(
    onActionClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacings.large),
                text = "Today",
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Medium),
                fontSize = 32.sp
            )
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(
                onClick = onActionClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "more",
                    tint = Blue
                )
            }
        }

    )
}
