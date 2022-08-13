package com.example.todotasker

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

sealed class Screens{

    data class Main(val name: String = "main"): Screens()
    data class NewNote(val name: String = "new_note"): Screens()
}

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Main().name){
        composable(Screens.Main().name){
            MainScreen(navController)
        }
        composable(Screens.NewNote().name){

        }
    }
}