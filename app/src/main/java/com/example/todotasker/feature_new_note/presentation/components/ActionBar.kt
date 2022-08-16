package com.example.todotasker.feature_new_note.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todotasker.ui.theme.Blue
import com.example.todotasker.ui.theme.spacings

@Composable
fun ActionBar(
    onCancelClick: () -> Unit,
    onDoneClick: () -> Unit,
) {

    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacings.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .clickable(role = Role.Button) { onCancelClick() },
                text = "Cancel",
                style = TextStyle(color = Blue),
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier
                    .clickable { onDoneClick() },
                text = "Done",
                fontWeight = FontWeight.Medium,
                style = TextStyle(color = Blue),
                fontSize = 18.sp
            )
        }
    }
}