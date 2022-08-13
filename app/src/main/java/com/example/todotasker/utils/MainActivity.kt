package com.example.todotasker.utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todotasker.feature_main_screen.presentation.MainScreen
import com.example.todotasker.ui.theme.ToDoTaskerTheme
import com.example.todotasker.utils.Navigation
import com.example.todotasker.utils.Screens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTaskerTheme {
                Navigation()
            }
        }
    }
}


