package com.example.todotasker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import com.example.todotasker.feature_main_screen.presentation.MainScreen
import com.example.todotasker.ui.theme.ToDoTaskerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTaskerTheme {
                MainScreen()
            }
        }
    }
}